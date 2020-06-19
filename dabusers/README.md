# DaBster-Users-Posts-Comments
This will be a Spring Boot API Integrated with CRUD functionality, allowing you to create a user, and that user to post posts, and comments on other users posts and so on. 


So to initialize a Spring Boot api with H2 and JPA you need to figure out how to get them to communicate with the databases. AKA H2. Do that by adding some configurations to the application.properties file inside the resources folder:
        # Configurations useful for working with H2
        spring.h2.console.enabled=true
        spring.h2.console.path=/h2-console
        #
        # We set a port that is not frequently used
        server.port=${PORT:2019}
        #
        # Feature that determines what happens when no accessors are found for a type
        # (and there are no annotations to indicate it is meant to be serialized).
        spring.jackson.serialization.FAIL_ON_EMPTY_BEANS=false
        #
        # keeps a transaction inside of the same entity manager
        # This property register an EntityManager to the current thread,
        # so you will have the same EntityManager until the web request is finished.
        spring.jpa.open-in-view=true
        #
        # What do with the schema
        # drop n create table again, good for testing
        spring.jpa.hibernate.ddl-auto=create
        spring.datasource.initialization-mode=always
        #
        
        

    Now that that's settled, The next step is to create your first table:
    
    We will create a users table with a few attributes. 
    The user table will contain the following:
    
    An Id,
    A name,
    An email,
    A Bio,
    
    
    Later on we will add a few more attributes after we have created other tables:
    A one to many relationship between user and Posts,
    and a one to many relationship between user and comments.
    Posts and comments will also have their own connection of one post to many comments, but many comments to one post.
    
    
    Posts Table (We will focus on this later, after we have made and implemented user):
    post id;
    foreign key for user id;
    a post title,
    a post body,
    a created_at date timestamp,
    an updated_at timestamp if it gets updated,
    
    
    
    Comments table (Again we will focus on this after we have gotten the users and posts tables done.)
    comment id,
    foreign id for post,
    foreign id for user,
    a comment body,
    a create_at date timestamp,
    and an updated_at timestamp for if it gets updated.
    
    