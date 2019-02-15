package org.knowm.xchange.coinmarketcappro.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.knowm.xchange.coinmarketcappro.dto.Result;
import org.knowm.xchange.coinmarketcappro.dto.Status;

/**
 * Result of https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol= call.
 *
 * <p>See also:
 * https://coinmarketcap.com/api/documentation/v1/#operation/getV1GlobalmetricsQuotesLatest
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class QuotesResponse extends Result<Map<String, SingleCoinQuote>> {

  /**
   * Constructor
   *
   * @param data The ticker data
   * @param status Status
   */
  public QuotesResponse(
      @JsonProperty("status") Status status,
      @JsonProperty("result") Map<String, SingleCoinQuote> data) {

    super(data, status);
  }
}
