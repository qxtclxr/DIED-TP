package test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class Test{

	@DisplayName("Pagerank")
	@Test
	public void test1() {

		int n1 = 2, n2 = 1;
		
		Assertions.assertEquals(3, n1+n2);
		
		
	}
	
}