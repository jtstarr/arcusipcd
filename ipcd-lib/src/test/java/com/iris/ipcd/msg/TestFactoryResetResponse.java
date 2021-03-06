package com.iris.ipcd.msg;

import static org.junit.Assert.*;

import java.io.InputStreamReader;

import org.junit.Test;

import com.iris.ipcd.msg.CommandType;
import com.iris.ipcd.msg.FactoryResetCommand;
import com.iris.ipcd.msg.FactoryResetResponse;
import com.iris.ipcd.msg.Status.Result;

public class TestFactoryResetResponse extends SerializerTest {

	@Test
	public void testDeser() throws Exception {
		
		FactoryResetResponse response1 = ser.fromJson(new InputStreamReader(this.getClass().getResourceAsStream("response-factoryreset-1.json")), FactoryResetResponse.class);
	
		assertNotNull(response1.getDevice());
		assertEquals("BlackBox", response1.getDevice().getVendor());
		assertEquals("Multisensor2", response1.getDevice().getModel());
		assertEquals("00049B3C7A05", response1.getDevice().getSn());
		assertEquals("1.0", response1.getDevice().getIpcdver());
		
		assertNotNull(response1.getRequest());
		assertEquals(CommandType.FactoryReset, response1.getRequest().getCommand());
		
		assertNotNull(response1.getStatus());
		assertEquals(Result.success, response1.getStatus().getResult());
		assertTrue(response1.getStatus().getMessages().isEmpty());
		
		assertNotNull(response1.getResponse());
		assertEquals(0, response1.getResponse().size());
		
	}
	
	@Test
	public void testSer() {
		
		FactoryResetResponse constructed = new FactoryResetResponse();
		
		FactoryResetCommand request = new FactoryResetCommand();
		
		constructed.setDevice(device);
		constructed.setRequest(request);
		constructed.setStatus(status);
		
		String json = ser.toJson(constructed);
		FactoryResetResponse deserialized = ser.fromJson(json, FactoryResetResponse.class);
		
		assertEquals(constructed, deserialized);
	}

}
