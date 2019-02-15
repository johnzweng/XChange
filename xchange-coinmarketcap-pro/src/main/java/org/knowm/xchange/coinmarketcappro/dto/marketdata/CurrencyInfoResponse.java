package org.knowm.xchange.coinmarketcappro.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.knowm.xchange.coinmarketcappro.dto.Result;
import org.knowm.xchange.coinmarketcappro.dto.Status;

/**
 * Result of https://pro-api.coinmarketcap.com/v1/cryptocurrency/info?symbol= call.
 *
 * <p>See also: https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyInfo
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class CurrencyInfoResponse extends Result<Map<String, SingleCoinInfo>> {

  /**
   * Constructor
   *
   * @param data coin info data
   * @param status Status
   */
  public CurrencyInfoResponse(
      @JsonProperty("status") Status status,
      @JsonProperty("result") Map<String, SingleCoinInfo> data) {

    super(data, status);
  }
}
