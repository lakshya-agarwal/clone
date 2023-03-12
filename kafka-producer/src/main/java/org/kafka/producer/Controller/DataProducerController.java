package org.kafka.producer.Controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.common.utilities.constants.CommonConstants;

@RestController
@RequestMapping("/webhooks")
public class DataProducerController {

	private static final Logger logger = LoggerFactory.getLogger(DataProducerController.class);

	@PostMapping("/jira-webhook/{pcmId}")
	public ResponseEntity<Map<String, Object>> handleWebhook(@RequestBody String payload, @PathVariable("pcmId") Integer pcmId) {
		logger.info("Received for pcm " + pcmId);

		Map<String, Object> result = new HashMap<>();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			result.put(CommonConstants.STATUS, CommonConstants.NO_DATA_FOUND);
		} // sleep for 2 seconds
		result.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		
		logger.info("Received Jira webhook: " + payload);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
