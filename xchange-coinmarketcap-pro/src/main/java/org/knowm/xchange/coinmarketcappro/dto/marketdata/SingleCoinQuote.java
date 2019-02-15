package org.knowm.xchange.coinmarketcappro.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Represents quote response (returned as part of /v1/cryptocurrency/quotes/latest endpoint) of a
 * single coin.
 *
 * <p>See https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyQuotesLatest
 * and there "response Schema" -> "data" -> "$key"
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class SingleCoinQuote {

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("circulating_supply")
  private BigDecimal circulatingSupply;

  @JsonProperty("last_updated")
  private Date lastUpdated;

  @JsonProperty("total_supply")
  private BigDecimal totalSupply;

  @JsonProperty("cmc_rank")
  private int cmcRank;

  @JsonProperty("platform")
  private Platform platform;

  @JsonProperty("tags")
  private List<String> tags;

  @JsonProperty("date_added")
  private Date dateAdded;

  @JsonProperty("quote")
  private Map<String, Quote> quote;

  @JsonProperty("num_market_pairs")
  private int numMarketPairs;

  @JsonProperty("name")
  private String name;

  @JsonProperty("max_supply")
  private BigDecimal maxSupply;

  @JsonProperty("id")
  private int id;

  @JsonProperty("slug")
  private String slug;

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setCirculatingSupply(BigDecimal circulatingSupply) {
    this.circulatingSupply = circulatingSupply;
  }

  public BigDecimal getCirculatingSupply() {
    return circulatingSupply;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setTotalSupply(BigDecimal totalSupply) {
    this.totalSupply = totalSupply;
  }

  public BigDecimal getTotalSupply() {
    return totalSupply;
  }

  public void setCmcRank(int cmcRank) {
    this.cmcRank = cmcRank;
  }

  public int getCmcRank() {
    return cmcRank;
  }

  public void setPlatform(Platform platform) {
    this.platform = platform;
  }

  public Platform getPlatform() {
    return platform;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setDateAdded(Date dateAdded) {
    this.dateAdded = dateAdded;
  }

  public Date getDateAdded() {
    return dateAdded;
  }

  public void setQuote(Map<String, Quote> quote) {
    this.quote = quote;
  }

  public Map<String, Quote> getQuote() {
    return quote;
  }

  public void setNumMarketPairs(int numMarketPairs) {
    this.numMarketPairs = numMarketPairs;
  }

  public int getNumMarketPairs() {
    return numMarketPairs;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setMaxSupply(BigDecimal maxSupply) {
    this.maxSupply = maxSupply;
  }

  public BigDecimal getMaxSupply() {
    return maxSupply;
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
    return "SingleCoinQuote{"
        + "symbol='"
        + symbol
        + '\''
        + ", circulatingSupply="
        + circulatingSupply
        + ", lastUpdated='"
        + lastUpdated
        + '\''
        + ", totalSupply="
        + totalSupply
        + ", cmcRank="
        + cmcRank
        + ", platform="
        + platform
        + ", tags="
        + tags
        + ", dateAdded='"
        + dateAdded
        + '\''
        + ", quote="
        + quote
        + ", numMarketPairs="
        + numMarketPairs
        + ", name='"
        + name
        + '\''
        + ", maxSupply="
        + maxSupply
        + ", id="
        + id
        + ", slug='"
        + slug
        + '\''
        + '}';
  }
}
