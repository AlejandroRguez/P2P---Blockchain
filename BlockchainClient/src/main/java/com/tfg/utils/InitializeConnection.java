package com.tfg.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

public class InitializeConnection {
	
	/**
	 * Conecta al socket al protocolo blockchain
	 * @return La sesión del socket 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static StompSession initialize() throws InterruptedException, ExecutionException {
		
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
	    container.setDefaultMaxBinaryMessageBufferSize(1024 * 1024);
	    container.setDefaultMaxTextMessageBufferSize(1024 * 1024);
		WebSocketClient simpleWebSocketClient = new StandardWebSocketClient(container);
		
		List<Transport> transports = new ArrayList<>(1);
		transports.add(new WebSocketTransport(simpleWebSocketClient));

		SockJsClient sockJsClient = new SockJsClient(transports);
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		String url = "ws://localhost:8080/blockchain";
		String sessionIdentifier = "spring-" + ThreadLocalRandom.current().nextInt(1, 99);
		StompSessionHandler sessionHandler = new MyStompSessionHandler(sessionIdentifier);
		return stompClient.connect(url, sessionHandler).get();
	}

}
