package org.knowm.xchange.coinmarketcappro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.knowm.xchange.coinmarketcappro.dto.marketdata.Quote;
import org.knowm.xchange.coinmarketcappro.dto.marketdata.SingleCoinQuote;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adapters to convert values returned by Coinmarketcap Pro API into Xchange core objects.
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class CoinmarketcapAdapters {

  protected static final Logger log = LoggerFactory.getLogger(CoinmarketcapAdapters.class);

  /**
   * Adapt return value from Coinmarketcap Pro into Ticker, takes the "counter" currency from the
   * given pair to choose the quote currency to return.
   */
  public static Ticker adaptTicker(SingleCoinQuote cmcQuote, CurrencyPair currencyPair) {

    String quoteCurrency = currencyPair.counter.getCurrencyCode();
    Quote quote = cmcQuote.getQuote().get(quoteCurrency); // use quote in "counter" currency

    Ticker.Builder builder = new Ticker.Builder();
    builder.currencyPair(currencyPair);
    builder.last(quote.getPrice());
    builder.ask(quote.getPrice());
    builder.bid(quote.getPrice());
    builder.timestamp(quote.getLastUpdated());
    builder.volume(quote.getVolume24h());

    return builder.build();
  }

  /** Convert map of quotes into List of Ticker */
  public static List<Ticker> adaptTickers(Map<String, SingleCoinQuote> cmcQuoteResults) {
    List<Ticker> tickers = new ArrayList<>();

    // iterate over each coin..
    cmcQuoteResults.forEach(
        (coin, singleCoinQuote) -> {
          if (singleCoinQuote.getQuote().isEmpty()) {
            log.warn(
                "returned map of quotes (JSON field 'quotes') for currency '%s' was empty. Omitted it in result.",
                singleCoinQuote.getSymbol());
            return;
          }

          // CMC supports to return several quotes (convert currencies) for a single currency at
          // once so we split them up into several pairs:

          // and iterate over all "quotes" (convert currencies) of a single coin..
          singleCoinQuote
              .getQuote()
              .forEach(
                  (quoteCurrency, quoteObject) -> {
                    CurrencyPair pair =
                        new CurrencyPair(singleCoinQuote.getSymbol(), quoteCurrency);
                    tickers.add(adaptTicker(singleCoinQuote, pair));
                  });
        });
    return tickers;
  }
}
