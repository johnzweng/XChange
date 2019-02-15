package org.knowm.xchange.coinmarketcappro.service;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinmarketcappro.CoinmarketcapPro;
import org.knowm.xchange.coinmarketcappro.dto.Result;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.ExchangeSecurityException;
import org.knowm.xchange.exceptions.FrequencyLimitExceededException;
import org.knowm.xchange.exceptions.FundsExceededException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.HttpStatusIOException;
import si.mazi.rescu.RestProxyFactory;

/**
 * Base service class
 *
 * @author Johannes Zweng, coinfinity.co
 */
public class CmcProBaseService extends BaseExchangeService implements BaseService {

  protected CoinmarketcapPro cmc;

  /** Constructor */
  public CmcProBaseService(Exchange exchange) {

    super(exchange);

    cmc =
        RestProxyFactory.createProxy(
            CoinmarketcapPro.class,
            exchange.getExchangeSpecification().getSslUri(),
            getClientConfig());
  }

  /** Parse errors from HTTP exceptions */
  protected <R> R checkException(HttpStatusIOException httpStatusException) {

    String msg = "HTTP Status: " + httpStatusException.getHttpStatusCode();

    // if we have a HTTP body try to parse more error details from body
    if (isNotEmpty(httpStatusException.getHttpBody())) {
      ObjectMapper mapper = new ObjectMapper();
      Result<R> result;
      try {
        result = mapper.readValue(httpStatusException.getHttpBody(), Result.class);
      } catch (Exception e1) {
        // but ignore errors on parsing and throw generic ExchangeException instead
        throw new ExchangeException(msg, httpStatusException);
      }
      // but if it contains a parsable result, then try to parse errors from result:
      if (result.getStatus() != null
          && isNotEmpty(result.getStatus().getErrorMessage())
          && !result.isSuccess()) {
        return checkResult(result);
      }
    }
    // else: just throw ExchangeException with causing Exception
    throw new ExchangeException(msg, httpStatusException);
  }

  /** Check results for errors */
  protected <R> R checkResult(Result<R> result) {

    if (!result.isSuccess()) {

      String error = result.getStatus().getErrorMessage();
      String msg = error + " - ErrorCode: " + result.getStatus().getErrorCode();

      if (result.getStatus().getErrorCode() == 401 || "This API Key is invalid.".equals(error)) {
        throw new ExchangeSecurityException(error);
      }
      if (result.getStatus().getErrorCode() == 402) {
        throw new FundsExceededException(error);
      }
      if (result.getStatus().getErrorCode() == 429) {
        throw new FrequencyLimitExceededException(error);
      }
      throw new ExchangeException(msg);
    }
    return result.getData();
  }
}
