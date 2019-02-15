package org.knowm.xchange.coinmarketcappro.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

/**
 * Represents info response (returned as part of /v1/cryptocurrency/info endpoint) of a single coin.
 *
 * <p>See https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyInfo and
 * there "response Schema" -> "data" -> "$key"
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class SingleCoinInfo {

  @JsonProperty("id")
  private int id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("category")
  private String category;

  @JsonProperty("slug")
  private String slug;

  @JsonProperty("logo")
  private String logo;

  @JsonProperty("tags")
  private List<String> tags;

  @JsonProperty("platform")
  private Platform platform;

  @JsonProperty("urls")
  private CoinUrls urls;

  @JsonProperty("date_added")
  private Date dateAdded;

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
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

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public CoinUrls getUrls() {
    return urls;
  }

  public void setUrls(CoinUrls urls) {
    this.urls = urls;
  }

  @Override
  public String toString() {
    return "SingleCoinInfo{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", symbol='"
        + symbol
        + '\''
        + ", category='"
        + category
        + '\''
        + ", slug='"
        + slug
        + '\''
        + ", logo='"
        + logo
        + '\''
        + ", tags="
        + tags
        + ", platform="
        + platform
        + ", urls="
        + urls
        + ", dateAdded="
        + dateAdded
        + '}';
  }
}
