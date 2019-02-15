package org.knowm.xchange.coinmarketcappro.dto.marketdata;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.junit.Test;

/** Coinmarketcap pro JSON parsing */
public class CoinmarketcapProQuoteJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is =
        CoinmarketcapProQuoteJSONTest.class.getResourceAsStream(
            "/org/knowm/xchange/coinmarketcappro/dto/marketdata/example-quotes-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    QuotesResponse cmcQuotes = mapper.readValue(is, QuotesResponse.class);

    Map<String, SingleCoinQuote> quotesData = cmcQuotes.getData();

    assertThat(quotesData).isNotNull();
    assertThat(quotesData).size().isEqualTo(3);

    assertThat(quotesData.get("ETH")).isNotNull();
    SingleCoinQuote eth = quotesData.get("ETH");
    assertThat(eth.getId()).isEqualTo(1027);
    assertThat(eth.getName()).isEqualTo("Ethereum");
    assertThat(eth.getSymbol()).isEqualTo("ETH");
    assertThat(eth.getSlug()).isEqualTo("ethereum");
    assertThat(eth.getNumMarketPairs()).isEqualTo(4255);
    assertThat(eth.getCmcRank()).isEqualTo(2);
    assertThat(eth.getTotalSupply()).isEqualByComparingTo("104844715.2491");
    assertThat(eth.getMaxSupply()).isNull();
    assertThat(eth.getLastUpdated()).isEqualTo("2019-02-13T17:23:18.000+0100");

    assertThat(quotesData.get("LTC")).isNotNull();
    SingleCoinQuote ltc = quotesData.get("LTC");
    assertThat(ltc.getName()).isEqualTo("Litecoin");
    assertThat(ltc.getMaxSupply()).isEqualByComparingTo("84000000");
    assertThat(ltc.getCirculatingSupply()).isEqualByComparingTo("60451499.7738777");
    assertThat(ltc.getTags()).size().isEqualTo(1);
    assertThat(ltc.getTags()).contains("mineable");
    assertThat(ltc.getPlatform()).isNull();

    assertThat(quotesData.get("PLAY")).isNotNull();
    SingleCoinQuote play = quotesData.get("PLAY");
    assertThat(play.getName()).isEqualTo("HEROcoin");
    assertThat(play.getSlug()).isEqualTo("herocoin");
    assertThat(play.getCirculatingSupply()).isEqualByComparingTo("127210773.639004");
    assertThat(play.getTotalSupply()).isEqualByComparingTo("252165028.705395");
    assertThat(play.getMaxSupply()).isNull();

    assertThat(play.getTags()).size().isZero();
    assertThat(play.getPlatform()).isNotNull();
    Platform playPlatform = play.getPlatform();
    assertThat(playPlatform.getId()).isEqualTo(1027);
    assertThat(playPlatform.getSlug()).isEqualTo("ethereum");
    assertThat(playPlatform.getName()).isEqualTo("Ethereum");
    assertThat(playPlatform.getTokenAddress())
        .isEqualTo("0xe477292f1b3268687a29376116b0ed27a9c76170");
  }
}
