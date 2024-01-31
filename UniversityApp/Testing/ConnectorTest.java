package Testing;
import App.Connector;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ConnectorTest {
    @Test
    public void testConnection() {
        Connector connector = new Connector();
        assertNotNull(connector.connection);
    }
}

