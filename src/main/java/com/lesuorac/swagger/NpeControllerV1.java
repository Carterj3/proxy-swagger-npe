package com.lesuorac.swagger;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
 

@RestController
public class NpeControllerV1 implements NpeResourceV1 {

	final Logger log = LoggerFactory.getLogger(getClass());
	
	@Async
	@RequestMapping(path="/", method = {RequestMethod.GET})
	@ApiOperation(value="The @Async causes 'com.sun.proxy' to be used which has a null packge")
	@Override
	public void asyncCausesNpe(@RequestParam(name="B") boolean b)
	{
		try {
			log.trace("NPE was called");
			Thread.sleep(TimeUnit.SECONDS.toMillis(10L));
		}catch(Exception e)
		{
			/* Don't care */
		}finally {
			log.trace("NPE finished");
		}
		
	}
}
