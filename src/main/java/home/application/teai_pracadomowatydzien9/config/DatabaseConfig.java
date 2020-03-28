package home.application.teai_pracadomowatydzien9.config;

/**ONLY FOR REMOTE MY SQL DATABASE **/

//@Configuration
//public class DatabaseConfig {
//
//    private DataSource dataSource;
//
//    @Autowired
//    public DatabaseConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(dataSource);
//    }
//
//    //INITIAL TABLE CREATION
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void start() {
//        String sql = "CREATE TABLE models(id int ,owner_gender varchar (255),owner_fullname varchar (255),owner_email varchar (255),owner_department varchar (255),owner_car_vin varchar (255), PRIMARY KEY (id))";
//        jdbcTemplate().update(sql);
//    }
//
//}
