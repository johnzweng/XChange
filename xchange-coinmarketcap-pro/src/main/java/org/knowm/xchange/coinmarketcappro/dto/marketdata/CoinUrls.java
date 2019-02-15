package org.knowm.xchange.coinmarketcappro.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * "urls" part of a single coin's /v1/cryptocurrency/info response.
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class CoinUrls {

  @JsonProperty("website")
  private List<String> website;

  @JsonProperty("explorer")
  private List<String> explorer;

  @JsonProperty("source_code")
  private List<String> sourceCode;

  @JsonProperty("message_board")
  private List<String> messageBoard;

  @JsonProperty("chat")
  private List<String> chat;

  @JsonProperty("announcement")
  private List<String> announcement;

  @JsonProperty("reddit")
  private List<String> reddit;

  @JsonProperty("twitter")
  private List<String> twitter;

  public void setWebsite(List<String> website) {
    this.website = website;
  }

  public List<String> getWebsite() {
    return website;
  }

  public void setTwitter(List<String> twitter) {
    this.twitter = twitter;
  }

  public List<String> getTwitter() {
    return twitter;
  }

  public void setMessageBoard(List<String> messageBoard) {
    this.messageBoard = messageBoard;
  }

  public List<String> getMessageBoard() {
    return messageBoard;
  }

  public void setChat(List<String> chat) {
    this.chat = chat;
  }

  public List<String> getChat() {
    return chat;
  }

  public void setReddit(List<String> reddit) {
    this.reddit = reddit;
  }

  public List<String> getReddit() {
    return reddit;
  }

  public void setExplorer(List<String> explorer) {
    this.explorer = explorer;
  }

  public List<String> getExplorer() {
    return explorer;
  }

  public void setSourceCode(List<String> sourceCode) {
    this.sourceCode = sourceCode;
  }

  public List<String> getSourceCode() {
    return sourceCode;
  }

  public void setAnnouncement(List<String> announcement) {
    this.announcement = announcement;
  }

  public List<String> getAnnouncement() {
    return announcement;
  }

  @Override
  public String toString() {
    return "CoinUrls{"
        + "website = '"
        + website
        + '\''
        + ",twitter = '"
        + twitter
        + '\''
        + ",message_board = '"
        + messageBoard
        + '\''
        + ",chat = '"
        + chat
        + '\''
        + ",reddit = '"
        + reddit
        + '\''
        + ",explorer = '"
        + explorer
        + '\''
        + ",source_code = '"
        + sourceCode
        + '\''
        + ",announcement = '"
        + announcement
        + '\''
        + "}";
  }
}
