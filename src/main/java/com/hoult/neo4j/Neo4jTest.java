package com.hoult.neo4j;

import org.neo4j.driver.v1.*;

public class Neo4jTest {
    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver("bolt://centos:8687", AuthTokens.basic("neo4j", "123456"));
        Session session = driver.session();

        session.run("CREATE (:Message {title: 'Hello', text: 'hello world'})");
        session.run("CREATE (:Language {name: 'Java8', version: '1.8'})");

        String createRelationship = "MATCH (m:Message),(l:Language) WHERE m.title = 'Hello' AND l.name='Java8'"
                + " CREATE (m)-[:HelloJava8]->(l);";
        session.run(createRelationship);

        String queryRelationship = "MATCH (m:Message)-[:HelloJava8]->" + "(l:Language {name:{language}}) "
                + "RETURN m.title,l.name;";
        StatementResult resultSet = session.run(queryRelationship, Values.parameters("language", "Java8"));

        while (resultSet.hasNext()) {
            Record result = resultSet.next();
            System.out.println(result.get("m.title") + " from " + result.get("l.name"));
        }

        session.close();
    }
}
