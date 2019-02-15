package org.knowm.xchange.coinmarketcappro;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.coinmarketcappro.dto.marketdata.CurrencyInfoResponse;
import org.knowm.xchange.coinmarketcappro.dto.marketdata.QuotesResponse;

/**
 * Coinmarketcap Pro API interface
 *
 * @author Johannes Zweng, coinfinity.co
 */
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
public interface CoinmarketcapPro {

  String API_KEY_HEADER = "X-CMC_PRO_API_KEY";

  @GET
  @Path("cryptocurrency/info")
  CurrencyInfoResponse getCurrencyInfo(
      @HeaderParam(API_KEY_HEADER) String apiKey, @QueryParam("symbol") String symbol)
      throws IOException;

  /**
   * See https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyQuotesLatest
   */
  @GET
  @Path("cryptocurrency/quotes/latest")
  QuotesResponse quotesLatest(
      @HeaderParam(API_KEY_HEADER) String apiKey,
      @QueryParam("symbol") String symbol,
      @QueryParam("convert") String convert)
      throws IOException;
}
