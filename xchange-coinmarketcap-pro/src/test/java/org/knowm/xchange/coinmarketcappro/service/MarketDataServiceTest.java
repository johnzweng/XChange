package org.knowm.xchange.coinmarketcappro.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.coinmarketcappro.CoinmarketcapProExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.exceptions.ExchangeSecurityException;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.marketdata.params.CurrencyPairsParam;

public class MarketDataServiceTest {

  // create a sandbox API Key for testing on https://sandbox.coinmarketcap.com/account
  private static final String API_KEY_SANDBOX = "xxxxxxxxx";

  private Exchange exchangeSandbox;
  private Exchange invalidApiKeyExchange;

  @Rule public ExpectedException exceptionRule = ExpectedException.none();

  @Before
  public void setUp() {

    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(CoinmarketcapProExchange.class.getCanonicalName());
    exchangeSpecification.setSslUri("https://sandbox-api.coinmarketcap.com");
    // productive API endpoint:
    // exchangeSpecification.setSslUri("https://pro-api.coinmarketcap.com");
    exchangeSpecification.setApiKey(API_KEY_SANDBOX);
    exchangeSandbox = ExchangeFactory.INSTANCE.createExchange(exchangeSpecification);

    // w/o API key set
    invalidApiKeyExchange =
        ExchangeFactory.INSTANCE.createExchange(CoinmarketcapProExchange.class.getCanonicalName());
  }

  @Test
  @Ignore // as it needs a valid sandbox API key
  public void getSingleQuoteUSD() throws Exception {

    MarketDataService marketDataService = exchangeSandbox.getMarketDataService();
    Ticker ticker = marketDataService.getTicker(new CurrencyPair("BTC", "USD"));
    // System.out.println(ticker.toString());
    assertThat(ticker).isNotNull();
    assertThat(ticker.getAsk()).isEqualByComparingTo(ticker.getBid());
    assertThat(ticker.getBid()).isEqualByComparingTo(ticker.getLast());
    assertThat(ticker.getAsk()).isPositive();
    assertThat(ticker.getAsk()).isGreaterThan(new BigDecimal("500"));
    assertThat(ticker.getAsk()).isLessThan(new BigDecimal("500000"));
    assertThat(ticker.getVolume()).isGreaterThan(new BigDecimal("10000000"));
  }

  @Test
  @Ignore // as it needs a valid sandbox API key
  public void getSingleQuoteEUR() throws Exception {

    MarketDataService marketDataService = exchangeSandbox.getMarketDataService();
    Ticker ticker = marketDataService.getTicker(new CurrencyPair("BTC", "EUR"));
    // System.out.println(ticker.toString());
    assertThat(ticker).isNotNull();
    assertThat(ticker.getAsk()).isEqualByComparingTo(ticker.getBid());
    assertThat(ticker.getBid()).isEqualByComparingTo(ticker.getLast());
    assertThat(ticker.getAsk()).isPositive();
    assertThat(ticker.getAsk()).isGreaterThan(new BigDecimal("500"));
    assertThat(ticker.getAsk()).isLessThan(new BigDecimal("500000"));
    assertThat(ticker.getVolume()).isGreaterThan(new BigDecimal("10000000"));
  }

  @Test
  @Ignore // as it needs a valid sandbox API key
  public void getSingleQuoteBTC() throws Exception {

    MarketDataService marketDataService = exchangeSandbox.getMarketDataService();
    Ticker ticker = marketDataService.getTicker(new CurrencyPair("PLAY", "BTC"));
    // System.out.println(ticker.toString());
    assertThat(ticker).isNotNull();
    assertThat(ticker.getAsk()).isEqualByComparingTo(ticker.getBid());
    assertThat(ticker.getBid()).isEqualByComparingTo(ticker.getLast());
    assertThat(ticker.getAsk()).isPositive();
  }

  @Test
  @Ignore // as it needs a valid sandbox API key
  public void getMultipleQuotesAtOnce() throws Exception {

    MarketDataService marketDataService = exchangeSandbox.getMarketDataService();
    CurrencyPairsParam pairsParam =
        () -> {
          Set<CurrencyPair> pairs = new HashSet<>();
          pairs.add(new CurrencyPair("LTC", "BTC"));
          pairs.add(new CurrencyPair("PLAY", "BTC"));
          pairs.add(new CurrencyPair("OMG", "BTC"));
          return pairs;
        };
    List<Ticker> tickers = marketDataService.getTickers(pairsParam);
    for (Ticker ticker : tickers) {
      System.out.println(ticker.toString());
      assertThat(ticker).isNotNull();
    }
  }

  @Test
  public void unauthenticatedTest() throws Exception {

    MarketDataService marketDataService = invalidApiKeyExchange.getMarketDataService();
    exceptionRule.expect(ExchangeSecurityException.class);
    exceptionRule.expectMessage("API key missing.");
    marketDataService.getTicker(new CurrencyPair("BTC", "USD"));
  }
}
