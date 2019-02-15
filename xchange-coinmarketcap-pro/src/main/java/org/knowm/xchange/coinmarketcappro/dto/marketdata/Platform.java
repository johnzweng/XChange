package org.knowm.xchange.coinmarketcappro.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * "platform" part of a single coin's /v1/cryptocurrency/quotes/latest response.
 *
 * <p>See https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyQuotesLatest
 * and there "response Schema" -> "data" -> "$key" -> "platform"
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class Platform {

  @JsonProperty("id")
  private int id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("slug")
  private String slug;

  @JsonProperty("token_address")
  private String tokenAddress;

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setTokenAddress(String tokenAddress) {
    this.tokenAddress = tokenAddress;
  }

  public String getTokenAddress() {
    return tokenAddress;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getSlug() {
    return slug;
  }

  @Override
  public String toString() {
    return "Platform{"
        + "symbol = '"
        + symbol
        + '\''
        + ",name = '"
        + name
        + '\''
        + ",token_address = '"
        + tokenAddress
        + '\''
        + ",id = '"
        + id
        + '\''
        + ",slug = '"
        + slug
        + '\''
        + "}";
  }
}
