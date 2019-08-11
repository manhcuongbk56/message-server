package cuong.test;

import io.gridgo.boot.support.annotations.EnableComponentScan;
import io.gridgo.boot.support.annotations.Registries;
import io.gridgo.boot.support.annotations.RegistryInitializer;
import io.gridgo.connector.ConnectorResolver;
import io.gridgo.connector.impl.resolvers.ClasspathConnectorResolver;
import io.gridgo.framework.support.Registry;
import io.sentry.Sentry;

@EnableComponentScan
@Registries(defaultProfile = "local")
public class Initializer {
    @RegistryInitializer
    public static void initRegistry(Registry registry) {
        Sentry.init(registry.lookup("sentry.dsn", String.class));
    }
}
