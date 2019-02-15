package org.knowm.xchange.coinmarketcappro.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.coinmarketcappro.CoinmarketcapProExchange;
import org.knowm.xchange.coinmarketcappro.dto.marketdata.SingleCoinInfo;
import org.knowm.xchange.currency.Currency;

public class CoinmarketcapProRawServiceTest {

  // create a sandbox API Key for testing on https://sandbox.coinmarketcap.com/account
  private static final String API_KEY_SANDBOX = "xxxxxxxxx";

  private Exchange exchangeSandbox;
  private CmcProMarketDataServiceRaw raw;

  @Before
  public void setUp() {

    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(CoinmarketcapProExchange.class.getCanonicalName());
    exchangeSpecification.setSslUri("https://sandbox-api.coinmarketcap.com");
    exchangeSpecification.setApiKey(API_KEY_SANDBOX);
    exchangeSandbox = ExchangeFactory.INSTANCE.createExchange(exchangeSpecification);
    raw = (CmcProMarketDataServiceRaw) exchangeSandbox.getMarketDataService();
  }

  @Test
  @Ignore // as it needs a valid sandbox API key
  public void getSingleCoinInfoBTC() throws Exception {

    SingleCoinInfo info = raw.getSingleCoinInfo(Currency.BTC);
    // System.out.println(info.toString());

    assertThat(info).isNotNull();
    assertThat(info.getId()).isEqualTo(1);
    assertThat(info.getName()).isEqualTo("Bitcoin");
    assertThat(info.getSymbol()).isEqualTo("BTC");
    assertThat(info.getCategory()).isEqualTo("coin");
    assertThat(info.getSlug()).isEqualTo("bitcoin");
    assertThat(info.getLogo()).isNotEmpty().startsWith("https://").endsWith(".png");
    assertThat(info.getTags()).isNotNull().contains("mineable").size().isGreaterThan(0);
    assertThat(info.getPlatform()).isNull();
    assertThat(info.getUrls()).isNotNull();
    assertThat(info.getUrls().getWebsite()).isNotNull().size().isNotZero();
    assertThat(info.getUrls().getMessageBoard()).isNotNull().size().isNotZero();
    assertThat(info.getUrls().getReddit()).isNotNull().size().isNotZero();
    assertThat(info.getUrls().getExplorer()).isNotNull().size().isNotZero();
    assertThat(info.getUrls().getSourceCode()).isNotNull().size().isNotZero();
    assertThat(info.getUrls().getAnnouncement()).isNotNull();
  }
}
