package com.cs.codingtest;

import com.cs.codingtest.request.Trade;
import com.cs.codingtest.response.TradeResultResponse;
import com.cs.codingtest.response.ValidationResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
public class TradeValidationClient {
    /**
     * Setting up logger
     */
    private static final Logger LOGGER = Logger.getLogger(TradeValidationClient.class.getName());
    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting REST Client!!!!");

        try {
            /*
                This is code to post and return a user object
             */
            RestTemplate rt = new RestTemplate();
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            rt.getMessageConverters().add(new StringHttpMessageConverter());
            String uri = new String("http://localhost:8080/api/tradeValidation");

            HttpEntity<List<Trade>> body = new HttpEntity(getTradeListFromJsonInput());

            ResponseEntity<TradeResultResponse> returns = rt.exchange(uri, HttpMethod.POST, body,
                    new ParameterizedTypeReference<TradeResultResponse>(){});
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            if(returns !=null && returns.getBody() !=null && !returns.getBody().getValidationResponses().isEmpty()) {
                for(ValidationResponse validationResponse:returns.getBody().getValidationResponses()) {
                    LOGGER.info(validationResponse.getValidationErrors() + "---->"
                            + gson.toJson(validationResponse.getLinkedTrade()));
                }
            }

        } catch (Exception e) {
            LOGGER.throwing(TradeValidationClient.class.getName(), "main", e);
        }

    }

    private static List<Trade> getTradeListFromJsonInput() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        List<Trade> tradeList = gson.fromJson("[ {\"customer\":\"PLUTO1\",\"ccyPair\":\"EURUSD\",\"type\":\"Spot\",\"direction\":\"BUY\",\"tradeDate\":\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"valueDate\":\"2016-08-15\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO1\",\"ccyPair\":\"EURUSD\",\"type\":\"Spot\",\"direction\":\"SELL\",\"tradeDate\":\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"valueDate\":\"2016-08-22\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO2\",\"ccyPair\":\"EURUSD\",\"type\":\"Forward\",\"direction\":\"SELL\",\"tradeDate\":\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"valueDate\":\"2016-08-22\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO2\",\"ccyPair\":\"EURUSD\",\"type\":\"Forward\",\"direction\":\"BUY\",\"tradeDate\":\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"valueDate\":\"2016-08-21\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO2\",\"ccyPair\":\"EURUSD\",\"type\":\"Forward\",\"direction\":\"BUY\",\"tradeDate\":\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"valueDate\":\"2016-08-08\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUT02\",\"ccyPair\":\"EURUSD\",\"type\":\"Forward\",\"direction\":\"BUY\",\"tradeDate\":\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"valueDate\":\"2016-08-08\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO3\",\"ccyPair\":\"EURUSD\",\"type\":\"Forward\",\"direction\":\"BUY\",\"tradeDate\":\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"valueDate\":\"2016-08-22\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO1\",\"ccyPair\":\"EURUSD\",\"type\":\"VanillaOption\",\"style\":\"EUROPEAN\",\"direction\":\"BUY\",\"strategy\":\"CALL\",\"tradeDate\" :\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"deliveryDate\":\"2016-08-22\",\"expiryDate\":\"2016-08-19\",\"payCcy\":\"USD\",\"premium\":0.20,\"premiumCcy\":\"USD\",\"premiumType\":\"%USD\",\"premiumDate\":\"2016-08-12\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO2\",\"ccyPair\":\"EURUSD\",\"type\":\"VanillaOption\",\"style\":\"EUROPEAN\",\"direction\":\"SELL\",\"strategy\":\"CALL\",\"tradeDate \":\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"deliveryDate\":\"2016-08-22\",\"expiryDate\":\"2016-08-21\",\"payCcy\":\"USD\",\"premium\":0.20,\"premiumCcy\":\"USD\",\"premiumType\":\"%USD\",\"premiumDate\":\"2016-08-12\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO1\",\"ccyPair\":\"EURUSD\",\"type\":\"VanillaOption\",\"style\":\"EUROPEAN\",\"direction\":\"BUY\",\"strategy\":\"CALL\",\"tradeDate\" :\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"deliveryDate\":\"2016-08-22\",\"expiryDate\":\"2016-08-25\",\"payCcy\":\"USD\",\"premium\":0.20,\"premiumCcy\":\"USD\",\"premiumType\":\"%USD\",\"premiumDate\":\"2016-08-12\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO1\",\"ccyPair\":\"EURUSD\",\"type\":\"VanillaOption\",\"style\":\"AMERICAN\",\"direction\":\"BUY\",\"strategy\":\"CALL\",\"tradeDate\" :\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"deliveryDate\":\"2016-08-22\",\"expiryDate\":\"2016-08-19\",\"excerciseStartDate\":\"2016-08-12\",\"payCcy\":\"USD\",\"premium\":0.20,\"premiumCcy\":\"USD\",\"premiumType\":\"%USD\",\"premiumDate\":\"2016-08-12\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO2\",\"ccyPair\":\"EURUSD\",\"type\":\"VanillaOption\",\"style\":\"AMERICAN\",\"direction\":\"SELL\",\"strategy\":\"CALL\",\"tradeDate \":\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"deliveryDate\":\"2016-08-22\",\"expiryDate\":\"2016-08-21\",\"excerciseStartDate\":\"2016-08-12\",\"payCcy\":\"USD\",\"premium\":0.20,\"premiumCcy\":\"USD\",\"premiumType\":\"%USD\",\"premiumDate\":\"2016-08-12\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO1\",\"ccyPair\":\"EURUSD\",\"type\":\"VanillaOption\",\"style\":\"AMERICAN\",\"direction\":\"BUY\",\"strategy\":\"CALL\",\"tradeDate\" :\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"deliveryDate\":\"2016-08-22\",\"expiryDate\":\"2016-08-25\",\"excerciseStartDate\":\"2016-08-12\",\"payCcy\":\"USD\",\"premium\":0.20,\"premiumCcy\":\"USD\",\"premiumType\":\"%USD\",\"premiumDate\":\"2016-08-12\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO1\",\"ccyPair\":\"EURUSD\",\"type\":\"VanillaOption\",\"style\":\"AMERICAN\",\"direction\":\"BUY\",\"strategy\":\"CALL\",\"tradeDate\" :\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"deliveryDate\":\"2016-08-22\",\"expiryDate\":\"2016-08-19\",\"excerciseStartDate\":\"2016-08-10\",\"payCcy\":\"USD\",\"premium\":0.20,\"premiumCcy\":\"USD\",\"premiumType\":\"%USD\",\"premiumDate\":\"2016-08-12\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"}, {\"customer\":\"PLUTO3\",\"ccyPair\":\"EURUSD\",\"type\":\"VanillaOption\",\"style\":\"AMERICAN\",\"direction\":\"SELL\",\"strategy\":\"CALL\",\"tradeDate \":\"2016-08-11\",\"amount1\":1000000.00,\"amount2\":1120000.00,\"rate\":1.12,\"deliveryDate\":\"2016-08-22\",\"expiryDate\":\"2016-08-19\",\"excerciseStartDate\":\"2016-08-10\",\"payCcy\":\"USD\",\"premium\":0.20,\"premiumCcy\":\"USD\",\"premiumType\":\"%USD\",\"premiumDate\":\"2016-08-12\",\"legalEntity\":\"CS Zurich\",\"trader\":\"Johann Baumfiddler\"} ]", new TypeToken<List<Trade>>(){}.getType());
        return tradeList;
    }
}
