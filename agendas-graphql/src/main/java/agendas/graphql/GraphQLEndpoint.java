package agendas.graphql;

import javax.servlet.annotation.WebServlet;

import agendas.servicio.ServicioAgendas;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import io.leangen.graphql.GraphQLSchemaGenerator;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/graphql", loadOnStartup = 1)
public class GraphQLEndpoint extends SimpleGraphQLServlet {

	public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
      
    	 return new GraphQLSchemaGenerator()
    		        .withOperationsFromSingletons(
    		            ServicioAgendas.getInstancia()
    		            )
    		        .generate();
    }
}