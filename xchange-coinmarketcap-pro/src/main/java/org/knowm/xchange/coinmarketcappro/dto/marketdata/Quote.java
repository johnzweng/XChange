package org.knowm.xchange.coinmarketcappro.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;

/**
 * "quote" part of a single coin's /v1/cryptocurrency/quotes/latest response.
 *
 * <p>See https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyQuotesLatest
 * and there "response Schema" -> "data" -> "$key" -> "quote"
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class Quote {

  @JsonProperty("percent_change_1h")
  private BigDecimal percentChange1h;

  @JsonProperty("last_updated")
  private Date lastUpdated;

  @JsonProperty("percent_change_24h")
  private BigDecimal percentChange24h;

  @JsonProperty("market_cap")
  private BigDecimal marketCap;

  @JsonProperty("price")
  private BigDecimal price;

  @JsonProperty("volume_24h")
  private BigDecimal volume24h;

  @JsonProperty("percent_change_7d")
  private BigDecimal percentChange7d;

  public void setPercentChange1h(BigDecimal percentChange1h) {
    this.percentChange1h = percentChange1h;
  }

  public BigDecimal getPercentChange1h() {
    return percentChange1h;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setPercentChange24h(BigDecimal percentChange24h) {
    this.percentChange24h = percentChange24h;
  }

  public BigDecimal getPercentChange24h() {
    return percentChange24h;
  }

  public void setMarketCap(BigDecimal marketCap) {
    this.marketCap = marketCap;
  }

  public BigDecimal getMarketCap() {
    return marketCap;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setVolume24h(BigDecimal volume24h) {
    this.volume24h = volume24h;
  }

  public BigDecimal getVolume24h() {
    return volume24h;
  }

  public void setPercentChange7d(BigDecimal percentChange7d) {
    this.percentChange7d = percentChange7d;
  }

  public BigDecimal getPercentChange7d() {
    return percentChange7d;
  }

  @Override
  public String toString() {
    return "Quote{"
        + "percentChange1h="
        + percentChange1h
        + ", lastUpdated='"
        + lastUpdated
        + '\''
        + ", percentChange24h="
        + percentChange24h
        + ", marketCap="
        + marketCap
        + ", price="
        + price
        + ", volume24h="
        + volume24h
        + ", percentChange7d="
        + percentChange7d
        + '}';
  }
}
