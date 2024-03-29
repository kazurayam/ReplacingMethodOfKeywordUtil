import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.logging.ErrorCollector
import com.kms.katalon.core.util.KeywordUtil
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.exception.StepFailedException

class TL1 {
	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuiteContext) {
		KeywordUtil.metaClass.static.markPassed = { String message ->
			// insert whatever lines of processing you want
			LocalDateTime localDateTime = LocalDateTime.now()
			GlobalVariable.TimeStamp = localDateTime.format(
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"))
			println "in markPassed Timestamp=${GlobalVariable.TimeStamp}"
			
			// the following 2 lines are copy&pasted from the original implementation
			logger.logPassed(message);
			ErrorCollector.getCollector().setKeywordPassed(true);
		
			// insert whatever lines of processing you want
		}
		
		KeywordUtil.metaClass.static.markFailed = { String message ->
			// additive
			LocalDateTime localDateTime = LocalDateTime.now()
			GlobalVariable.TimeStamp = localDateTime.format(
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"))
			println "in markFailed Timestamp=${GlobalVariable.TimeStamp}"
			
			// the original implementation
			ErrorCollector.getCollector().addError(new StepFailedException(message))
		}
		
		KeywordUtil.metaClass.static.markFailedAndStop = { String message ->
			// additive
			LocalDateTime localDateTime = LocalDateTime.now()
			GlobalVariable.TimeStamp = localDateTime.format(
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"))
			println "in markupFailedAndStop Timestamp=${GlobalVariable.TimeStamp}"
			
			// the original implementation
			throw new StepFailedException(message)
			
		}
	}
}