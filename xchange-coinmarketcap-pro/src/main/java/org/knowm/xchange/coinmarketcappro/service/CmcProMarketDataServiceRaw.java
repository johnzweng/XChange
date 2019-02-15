package org.knowm.xchange.coinmarketcappro.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinmarketcappro.dto.marketdata.CurrencyInfoResponse;
import org.knowm.xchange.coinmarketcappro.dto.marketdata.QuotesResponse;
import org.knowm.xchange.coinmarketcappro.dto.marketdata.SingleCoinInfo;
import org.knowm.xchange.coinmarketcappro.dto.marketdata.SingleCoinQuote;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import si.mazi.rescu.HttpStatusIOException;

/**
 * Raw MarketData service
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class CmcProMarketDataServiceRaw extends CmcProBaseService {

  /** Constructor */
  public CmcProMarketDataServiceRaw(Exchange exchange) {

    super(exchange);
  }

  /**
   * Fetch quote (ticker) for a single currency and a single counter currency see:
   * https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyQuotesLatest
   *
   * <p>API endpoint: "/v1/cryptocurrency/quotes/latest" see:
   * https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyQuotesLatest
   */
  public SingleCoinQuote getSingleCoinTicker(CurrencyPair currencyPair) throws IOException {

    QuotesResponse quotesResponse;
    try {
      quotesResponse =
          cmc.quotesLatest(
              exchange.getExchangeSpecification().getApiKey(),
              currencyPair.base.getCurrencyCode(),
              currencyPair.counter.getCurrencyCode());
    } catch (HttpStatusIOException e) {
      return checkException(e);
    }

    return checkResult(quotesResponse).get(currencyPair.base.getCurrencyCode());
  }

  /**
   * Fetch quote (ticker) for multiple currencies in a single call (supporting multiple counter
   * currencies, if your plan allows it)
   *
   * <p>API endpoint: "/v1/cryptocurrency/quotes/latest" see:
   * https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyQuotesLatest
   */
  public Map<String, SingleCoinQuote> getMultipleCoinTickers(
      Set<Currency> coins, Set<Currency> convertCurrencies) throws IOException {

    List<String> coinSymbols = coins.stream().map(c -> c.getSymbol()).collect(Collectors.toList());
    String commaSeparatedCoins = StringUtils.join(coinSymbols, ",");
    List<String> convertSymbols =
        convertCurrencies.stream().map(c -> c.getSymbol()).collect(Collectors.toList());
    String commaSeparatedConvertCurrencies = StringUtils.join(convertSymbols, ",");

    QuotesResponse quotesResponse;
    try {
      quotesResponse =
          cmc.quotesLatest(
              exchange.getExchangeSpecification().getApiKey(),
              commaSeparatedCoins,
              commaSeparatedConvertCurrencies);
    } catch (HttpStatusIOException e) {
      return checkException(e);
    }

    return checkResult(quotesResponse);
  }

  /**
   * Fetch infos for a single currency.
   *
   * <p>API endpoint: "/v1/cryptocurrency/info" see:
   * https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyInfo
   */
  public SingleCoinInfo getSingleCoinInfo(Currency currency) throws IOException {

    CurrencyInfoResponse infoResponse;
    try {
      infoResponse =
          cmc.getCurrencyInfo(
              exchange.getExchangeSpecification().getApiKey(), currency.getCurrencyCode());
    } catch (HttpStatusIOException e) {
      return checkException(e);
    }

    return checkResult(infoResponse).get(currency.getCurrencyCode());
  }

  /**
   * Fetch infos for multiple currencies in a single call.
   *
   * <p>API endpoint: "/v1/cryptocurrency/info" see:
   * https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyInfo
   */
  public Map<String, SingleCoinInfo> getMultipleCoinInfos(Set<Currency> coins) throws IOException {

    List<String> coinSymbols = coins.stream().map(Currency::getSymbol).collect(Collectors.toList());
    String commaSeparatedCoins = StringUtils.join(coinSymbols, ",");

    CurrencyInfoResponse infoResponse;
    try {
      infoResponse =
          cmc.getCurrencyInfo(exchange.getExchangeSpecification().getApiKey(), commaSeparatedCoins);
    } catch (HttpStatusIOException e) {
      return checkException(e);
    }

    return checkResult(infoResponse);
  }
}
