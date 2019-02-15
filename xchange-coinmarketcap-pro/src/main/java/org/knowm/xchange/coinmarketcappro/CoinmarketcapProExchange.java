package org.knowm.xchange.coinmarketcappro;

import java.io.IOException;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.coinmarketcappro.service.CmcProMarketDataService;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

/**
 * Coinmarketcap Pro implementation.
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class CoinmarketcapProExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  protected void initServices() {
    if (this.marketDataService == null) {
      this.marketDataService = new CmcProMarketDataService(this);
    }
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://pro-api.coinmarketcap.com");
    exchangeSpecification.setHost("pro-api.coinmarketcap.com");
    exchangeSpecification.setPort(443);
    exchangeSpecification.setExchangeName("CoinMarketCap");
    exchangeSpecification.setExchangeDescription(
        "Cryptocurrency market cap rankings, charts, and more.");
    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }

  @Override
  public ExchangeSpecification getExchangeSpecification() {
    if (exchangeSpecification == null) {
      exchangeSpecification = getDefaultExchangeSpecification();
    }
    return exchangeSpecification;
  }

  @Override
  public ExchangeMetaData getExchangeMetaData() {
    return null;
  }

  @Override
  public void remoteInit() throws ExchangeException {
    initServices();
  }
}
