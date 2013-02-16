package ro.ratoi.virgiliu.marlon

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * 
 * @author virgiliu
 *
 */
final class AbbreviatorTest {

	private static final int MAX_LENGTH = 10

	private Abbreviator abb

	private static final def INPUT = [
		'',
		'LOA',
		'Other LOA',
		'Dentures',
		'Partial',
		'Bolus Formation',
		'Laryngeal Exursion',
		'Swallow Delay',
		'Throat Clear',
		'Pharyngeal Comment',
		'Esophageal Concerns',
		'Esophageal Concerns Description',
		'PICC Insertion Date',
		'Dressing Changed',
		'Humidity',
		'GT Tube 1 Cm',
		'ADLNN GT Tube 2 Cm',
		'ADLNN GT Tube 3 Cm',
		'Home Med Profile Reviewed',
		'Spoke Patient Bedside',
		'Reviewed Meds With Patient',
		'Potential Med Side Effects',
		'Education Handouts For Patient',
		'Medications Questions Answered',
		'Social Services Consulted',
		'Unable to Educate Patient',
		'Comments',
		'ADLNN Dressing Changed',
		'ADLNN Humidity',
		'ADLNN PICC Insertion Date',
		'GI GT Tube 1 Cm',
		'GI GT Tube 2 Cm',
		'GI GT Tube 3 Cm',
		'Type~!@#$%^&*()+\\[]{};,.<>?"/Size',
	]
	private static final def OUTPUT = [
		'',
		'LOA',
		'Other LOA',
		'Dentures',
		'Partial',
		'BolusFrmtn',
		'LrynExrsn',
		'SwllwDelay',
		'ThrtClear',
		'PhryCmmnt',
		'EsphCncr',
		'EsCnDs',
		'PIInDt',
		'DrssChngd',
		'Humidity',
		'GTTube1Cm',
		'ADLNNGTTb',
		//'AGTTb2Cm',
		'ADLNNGTTb',
		//'AGTTb3Cm',
		'HmMdPrfl',
		'SpkPtBd',
		'RvwdMdsWth',
		//'RMWP',
		'PtntlMdSd',
		//'PMdSdE',
		'EdctnHndt',
		//'EHFrP',
		'MdQsAn',
		'SclSrCn',
		'UnbltEdct',
		//'UtoEP',
		'Comments',
		'ADDrCh',
		'ADLNNHmdty',
		'ADLNNPICC',
		//'APIDt',
		'GIGTTb1Cm',
		'GIGTTb2Cm',
		'GIGTTb3Cm',
		'TypeSize',
	]

	@Before
	public final void setUp() throws Exception {
		abb = new Abbreviator()
	}

	@After
	public final void tearDown() throws Exception {
		abb = null
	}

	@Test
	public final void testAbbreviate() {
		int i = 0
		for (inp in INPUT) {
			assertEquals(OUTPUT[i++], abb.abbreviate(inp, MAX_LENGTH))
		}
	}
}
