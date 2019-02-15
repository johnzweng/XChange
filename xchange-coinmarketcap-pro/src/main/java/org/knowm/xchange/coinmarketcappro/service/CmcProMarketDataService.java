package org.knowm.xchange.coinmarketcappro.service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinmarketcappro.CoinmarketcapAdapters;
import org.knowm.xchange.coinmarketcappro.dto.marketdata.SingleCoinQuote;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.marketdata.params.CurrencyPairsParam;
import org.knowm.xchange.service.marketdata.params.Params;

/**
 * MarketData service
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class CmcProMarketDataService extends CmcProMarketDataServiceRaw
    implements MarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public CmcProMarketDataService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    return CoinmarketcapAdapters.adaptTicker(getSingleCoinTicker(currencyPair), currencyPair);
  }

  @Override
  public List<Ticker> getTickers(Params params) throws IOException {

    if (!(params instanceof CurrencyPairsParam)) {
      throw new IllegalArgumentException("Params must be instance of CurrencyPairsParam");
    }
    Collection<CurrencyPair> pairs = ((CurrencyPairsParam) params).getCurrencyPairs();

    Set<Currency> coins = new HashSet<>();
    Set<Currency> converts = new HashSet<>();
    for (CurrencyPair pair : pairs) {
      coins.add(pair.base);
      converts.add(pair.counter);
    }
    Map<String, SingleCoinQuote> multipleCoinTickers = getMultipleCoinTickers(coins, converts);
    return CoinmarketcapAdapters.adaptTickers(multipleCoinTickers);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... objects) {
    throw new NotAvailableFromExchangeException();
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... objects) {
    throw new NotAvailableFromExchangeException();
  }
}
