package JDBC;

import Contracts.Contract;
import Contracts.DigitalTV;
import Contracts.MobileConnection;
import Contracts.WiredInternet;
import PeoplesInformation.Human;
import Repository.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to work with JDBC library
 * read and write repo to database
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class JDBCWorker {
    /**
     * constants
     */
    public static final String defaultUrl = "jdbc:postgresql://localhost:5433/repository";

    public static final String defaultUser = "postgres";

    public static final String defaultPassword = "1234";
    /**
     * jdbc connection
     */
    private Connection connection;

    /**
     *creating connection by url, user and password
     */
    public JDBCWorker(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    /**
     * method to write repo to DB
     * @param repository repo,that we need to write to DB
     * @param repId repositories id
     * @param <T> parametrization type
     * @throws SQLException
     */
    public <T extends Contract> void writeRepo(Repository<T> repository, int repId) throws SQLException {
        LocalDateConverter converter = new LocalDateConverter();
        String className = repository.getContracts()[0].getClass().getSimpleName();
        String insertRepo = "insert into repository values\n" +
                "(?,?,?);";
        PreparedStatement repoStatement = connection.prepareStatement(insertRepo);
        repoStatement.setInt(1, repId);
        repoStatement.setInt(2, repository.getSize());
        repoStatement.setString(3, className);
        repoStatement.executeUpdate();
        String insertContractStatement;
        System.out.println(className);
        if (className.equals("DigitalTV")) {
                for (int i=0;i<repository.getSize();i++) {
                    insertContractStatement = "insert into digitaltv values\n" +
                            "(?,?,?,?,?,?,?,?,?,?,?);";
                    PreparedStatement contractStatement = connection.prepareStatement(insertContractStatement);
                    contractStatement.setInt(1, repository.getByIndex(i).getId());
                    contractStatement.setDate(2, converter.convertToDatabaseColumn(repository.getByIndex(i).getStartContract()));
                    contractStatement.setDate(3, converter.convertToDatabaseColumn(repository.getByIndex(i).getEndContract()));
                    contractStatement.setLong(4, repository.getByIndex(i).getNumberOfContract());
                    contractStatement.setInt(5, ((WiredInternet) repository.getByIndex(i)).getConnectionSpeed());
                    contractStatement.setLong(6, repository.getByIndex(i).getOwner().getId());
                    contractStatement.setString(7, repository.getByIndex(i).getOwner().getFio());
                    contractStatement.setDate(8, converter.convertToDatabaseColumn(repository.getByIndex(i).getOwner().getBornDate()));
                    contractStatement.setString(9, repository.getByIndex(i).getOwner().getPassport());
                    contractStatement.setInt(10, repository.getByIndex(i).getOwner().getAge());
                    contractStatement.setInt(11, repId);
                    contractStatement.executeUpdate();
            }
        }
        if (className.equals("MobileConnection"))
                for (int i = 0; i < repository.getSize(); i++) {
                    insertContractStatement = "insert into mobileconnection values\n" +
                            "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
                    PreparedStatement contractStatement=connection.prepareStatement(insertContractStatement);
                    contractStatement.setInt(1, repository.getByIndex(i).getId());
                    contractStatement.setDate(2, converter.convertToDatabaseColumn(repository.getByIndex(i).getStartContract()));
                    contractStatement.setDate(3, converter.convertToDatabaseColumn(repository.getByIndex(i).getEndContract()));
                    contractStatement.setLong(4, repository.getByIndex(i).getNumberOfContract());
                    contractStatement.setInt(5, ((MobileConnection) repository.getByIndex(i)).getNumberOfMinutes());
                    contractStatement.setInt(6, ((MobileConnection) repository.getByIndex(i)).getNumberOfSMS());
                    contractStatement.setInt(7, ((MobileConnection) repository.getByIndex(i)).getInternetTraffic());
                    contractStatement.setInt(8, repository.getByIndex(i).getOwner().getId());
                    contractStatement.setString(9, repository.getByIndex(i).getOwner().getFio());
                    contractStatement.setDate(10, converter.convertToDatabaseColumn(repository.getByIndex(i).getOwner().getBornDate()));
                    contractStatement.setString(11, repository.getByIndex(i).getOwner().getPassport());
                    contractStatement.setInt(12, repository.getByIndex(i).getOwner().getAge());
                    contractStatement.setInt(13, repId);
                    contractStatement.executeUpdate();
            }
        if (className.equals("WiredInternet")) {
            for (int i=0;i<repository.getSize();i++) {
                insertContractStatement = "insert into wiredinternet values\n" +
                        "(?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement contractStatement = connection.prepareStatement(insertContractStatement);
                contractStatement.setInt(1, repository.getByIndex(i).getId());
                contractStatement.setDate(2, converter.convertToDatabaseColumn(repository.getByIndex(i).getStartContract()));
                contractStatement.setDate(3, converter.convertToDatabaseColumn(repository.getByIndex(i).getEndContract()));
                contractStatement.setLong(4, repository.getByIndex(i).getNumberOfContract());
                contractStatement.setInt(5, ((WiredInternet) repository.getByIndex(i)).getConnectionSpeed());
                contractStatement.setLong(6, repository.getByIndex(i).getOwner().getId());
                contractStatement.setString(7, repository.getByIndex(i).getOwner().getFio());
                contractStatement.setDate(8, converter.convertToDatabaseColumn(repository.getByIndex(i).getOwner().getBornDate()));
                contractStatement.setString(9, repository.getByIndex(i).getOwner().getPassport());
                contractStatement.setInt(10, repository.getByIndex(i).getOwner().getAge());
                contractStatement.setInt(11, repId);
                contractStatement.executeUpdate();
            }
        }
    }

    /**
     * method to read repo from DB
     * @param repoId id of repository
     * @param type type of repository
     * @param <T> parametrization type
     * @return new repository
     */
    public <T extends Contract> Repository<T> readRepo(int repoId, String type){
        String sql;
        LocalDateConverter converter=new LocalDateConverter();
        PreparedStatement contractStatement;
        try(Statement statement=connection.createStatement()) {
            if (type.equals("DigitalTV")) {
                Repository<DigitalTV> mobileConnectionRepository =new Repository<DigitalTV>();
                sql = "select r.rep_size,\n" +
                        "       mc.dt_id,\n" +
                        "       mc.dt_start,\n" +
                        "       mc.dt_end,\n" +
                        "       mc.dt_number,\n" +
                        "       mc.dt_channels,\n" +
                        "       mc.h_id,\n" +
                        "       mc.h_name,\n" +
                        "       mc.h_borndate,\n" +
                        "       mc.h_passport,\n" +
                        "       mc.h_age\n" +
                        "  from repository r\n" +
                        "    inner join digitaltv mc\n" +
                        "      on r.rep_id=mc.rep_id  \n" +
                        ";";
                contractStatement=connection.prepareStatement(sql);
                try (ResultSet resultSet=statement.executeQuery(sql)){
                    while (resultSet.next()){
                        DigitalTV mobileConnection=new DigitalTV();
                        mobileConnection.setId(resultSet.getInt("wi_id"));
                        mobileConnection.setStartContract(converter.convertToEntityAttribute(resultSet.getDate("wi_start")));
                        mobileConnection.setEndContract(converter.convertToEntityAttribute(resultSet.getDate("wi_end")));
                        mobileConnection.setNumberOfContract(resultSet.getInt("wi_number"));
                        List<String> channels=new ArrayList<>();
                        channels.add(resultSet.getString("dt_channels"));
                        mobileConnection.setChannels(channels);
                        Human human=new Human();
                        human.setBornDate(converter.convertToEntityAttribute(resultSet.getDate("h_borndate")));
                        human.setFio(resultSet.getString("h_name"));
                        human.setPassport(resultSet.getString("h_passport"));
                        human.setId(resultSet.getInt("h_id"));
                        human.setAge(resultSet.getInt("h_age"));
                        human.setFio(resultSet.getString("h_name"));
                        mobileConnection.setOwner(human);
                        mobileConnectionRepository.add(mobileConnection);
                    }
                }
                return (Repository<T>) mobileConnectionRepository;
            }
            if (type.equals("MobileConnection")) {
                    Repository<MobileConnection> mobileConnectionRepository =new Repository<MobileConnection>();
                    sql = "select r.rep_size,\n" +
                            "       mc.mc_id,\n" +
                            "       mc.mc_start,\n" +
                            "       mc.mc_end,\n" +
                            "       mc.mc_number,\n" +
                            "       mc.mc_minutes,\n" +
                            "       mc.mc_sms,\n" +
                            "       mc.mc_traffic,\n" +
                            "       mc.h_id,\n" +
                            "       mc.h_name,\n" +
                            "       mc.h_borndate,\n" +
                            "       mc.h_passport,\n" +
                            "       mc.h_age\n" +
                            "  from repository r\n" +
                            "    inner join mobileconnection mc\n" +
                            "      on r.rep_id=mc.rep_id;" ;
                    contractStatement=connection.prepareStatement(sql);
            try (ResultSet resultSet=statement.executeQuery(sql)){
                while (resultSet.next()){
                    MobileConnection mobileConnection=new MobileConnection();
                    mobileConnection.setId(resultSet.getInt("mc_id"));
                    mobileConnection.setStartContract(converter.convertToEntityAttribute(resultSet.getDate("mc_start")));
                    mobileConnection.setEndContract(converter.convertToEntityAttribute(resultSet.getDate("mc_end")));
                    mobileConnection.setNumberOfContract(resultSet.getInt("mc_number"));
                    mobileConnection.setNumberOfMinutes(resultSet.getInt("mc_minutes"));
                    mobileConnection.setNumberOfSMS(resultSet.getInt("mc_sms"));
                    mobileConnection.setInternetTraffic(resultSet.getInt("mc_traffic"));
                    Human human=new Human();
                    human.setFio(resultSet.getString("h_name"));
                    human.setId(resultSet.getInt("h_id"));
                    human.setBornDate(converter.convertToEntityAttribute(resultSet.getDate("h_borndate")));
                    human.setPassport(resultSet.getString("h_passport"));
                    human.setAge(resultSet.getInt("h_age"));
                    mobileConnection.setOwner(human);
                    mobileConnectionRepository.add(mobileConnection);
                }
                }
            return (Repository<T>) mobileConnectionRepository;
            }
            if (type.equals("WiredInternet")) {
                Repository<WiredInternet> mobileConnectionRepository =new Repository<WiredInternet>();
                sql = "select r.rep_size,\n" +
                        "       mc.wi_id,\n" +
                        "       mc.wi_start,\n" +
                        "       mc.wi_end,\n" +
                        "       mc.wi_number,\n" +
                        "       mc.wi_speed,\n" +
                        "       mc.h_id,\n" +
                        "       mc.h_name,\n" +
                        "       mc.h_borndate,\n" +
                        "       mc.h_passport,\n" +
                        "       mc.h_age\n" +
                        "  from repository r\n" +
                        "    inner join wiredinternet mc\n" +
                        "      on r.rep_id=mc.rep_id  \n" +
                        ";";
                contractStatement=connection.prepareStatement(sql);
                try (ResultSet resultSet=statement.executeQuery(sql)){
                    while (resultSet.next()){
                        WiredInternet mobileConnection=new WiredInternet();
                        mobileConnection.setId(resultSet.getInt("wi_id"));
                        mobileConnection.setStartContract(converter.convertToEntityAttribute(resultSet.getDate("wi_start")));
                        mobileConnection.setEndContract(converter.convertToEntityAttribute(resultSet.getDate("wi_end")));
                        mobileConnection.setNumberOfContract(resultSet.getInt("wi_number"));
                        mobileConnection.setConnectionSpeed(resultSet.getInt("wi_speed"));
                        Human human=new Human();
                        human.setId(resultSet.getInt("h_id"));
                        human.setBornDate(converter.convertToEntityAttribute(resultSet.getDate("h_borndate")));
                        human.setPassport(resultSet.getString("h_passport"));
                        human.setAge(resultSet.getInt("h_age"));
                        mobileConnection.setOwner(human);
                        mobileConnectionRepository.add(mobileConnection);
                    }
                }
                return (Repository<T>) mobileConnectionRepository;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * method to clear DB
     */
    public void rollback() throws SQLException {
        String sql="delete from mobileconnection;\n" +
                "delete from wiredinternet;\n" +
                "delete from digitaltv;\n" +
                "delete from repository;";
        PreparedStatement st=connection.prepareStatement(sql);
        st.executeUpdate();
    }
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
