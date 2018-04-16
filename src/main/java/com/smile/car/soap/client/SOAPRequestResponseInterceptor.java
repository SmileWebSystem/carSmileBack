package com.smile.car.soap.client;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Binding;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;



public class SOAPRequestResponseInterceptor implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		loggerXMLContext(context);
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		loggerXMLContext(context);
		return true;
	}

	private void loggerXMLContext(SOAPMessageContext smc) {
		Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outboundProperty.booleanValue()) {
//			LoggerUtils.info("\nRequest message:");
		} else {
//			LoggerUtils.info("\nResponse message:");
		}

		SOAPMessage message = smc.getMessage();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			message.writeTo(baos);
//			LoggerUtils.info(baos.toString());
		} catch (Exception e) {
//			LoggerUtils.error("Error getting xml context Logger", e);
		}
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	@SuppressWarnings("rawtypes")
	/**
	 * This static method adds the handler to the provided port's binding
	 * object.
	 * 
	 * @param binding
	 *            - The binding object can be fetched by
	 *            <code>((BindingProvider) port).getBinding()</code>
	 */
	public static void addToPort(Binding binding) {
		List<Handler> handlerChain = binding.getHandlerChain();
		handlerChain.add(new SOAPRequestResponseInterceptor());

		/*
		 * Check List<Handler> javax.xml.ws.Binding.getHandlerChain() javadocs.
		 * It states: Gets a copy of the handler chain for a protocol binding
		 * instance. If the returned chain is modified a call to setHandlerChain
		 * is required to configure the binding instance with the new chain.
		 */
		binding.setHandlerChain(handlerChain);
	}
}
