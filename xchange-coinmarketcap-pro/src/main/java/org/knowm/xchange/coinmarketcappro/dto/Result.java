package org.knowm.xchange.coinmarketcappro.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Generic resturn value structure for every API endpoint. See also:
 * https://coinmarketcap.com/api/documentation/v1/#section/Standards-and-Conventions
 *
 * @author Johannes Zweng
 */
public class Result<V> {

  /** "data" contains result of the queried enpoint */
  private final V data;

  /** "status" is always present and contains status info about the returned call */
  private final Status status;

  /**
   * Constructor
   *
   * @param data
   * @param status
   */
  @JsonCreator
  public Result(@JsonProperty("data") V data, @JsonProperty("status") Status status) {

    this.data = data;
    this.status = status;
  }

  public boolean isSuccess() {

    // error code = 0 indicates success
    return status.getErrorCode() == 0;
  }

  public V getData() {

    return data;
  }

  public Status getStatus() {

    return status;
  }

  @Override
  public String toString() {
    return "Result{" + "data=" + data + ", status=" + status + '}';
  }
}
