package eu.grayroot.anarchycore;

import eu.grayroot.anarchycore.command.HubCommand;
import eu.grayroot.anarchycore.command.ReportCommand;
import eu.grayroot.anarchycore.command.VoteCommand;
import eu.grayroot.anarchycore.listener.ChatListener;
import eu.grayroot.anarchycore.listener.CommandListener;
import eu.grayroot.anarchycore.listener.LoginListener;
import eu.grayroot.anarchycore.listener.MoveListener;
import eu.grayroot.anarchycore.object.AnarchyServer;
import eu.grayroot.anarchycore.sql.ServerData;
import eu.grayroot.anarchycore.sql.SqlConnection;
import eu.grayroot.anarchycore.utils.BroadcastUtils;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnarchyCore extends JavaPlugin {

    private static AnarchyCore instance;
    private static SqlConnection sql;
    private static Connection sqlConnection;

    private static List<Player> loginPlayers;
    private static AnarchyServer anarchyServer;

    @Override
    public void onEnable() {
        instance = this;
        sql = new SqlConnection("jdbc:mysql://", "127.0.0.1", 3306, "dbName", "dbUser", "dbPassword");
        try {
            sql.connect();
            log("Successfully connected to SQL Database!");
        } catch (SQLException | ClassNotFoundException e) {
            log("Unable to connect to Database! Closing the server");
            getServer().shutdown();
            e.printStackTrace();
        }
        sqlConnection = sql.getConnection();
        anarchyServer = new AnarchyServer(0, getServer().getServerName(), getServer().getOnlinePlayers().size(), getServer().getMaxPlayers(), true);
        if(!new ServerData(getConnection()).isRegistered(anarchyServer)) {
            new ServerData(getConnection()).registerServer(anarchyServer);
        } else {
            new ServerData(getConnection()).updateServerData(anarchyServer);
        }
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        loginPlayers = new ArrayList<>();
        registerEvents();
        registerCommands();
        new BroadcastUtils().broadcastScheduler();
    }

    @Override
    public void onDisable() {
        if(!new ServerData(getConnection()).isRegistered(anarchyServer)) {
            new ServerData(getConnection()).registerServer(anarchyServer);
        } else {
            anarchyServer.setStatus(false);
            new ServerData(getConnection()).updateServerData(anarchyServer);
        }
        sql.disconnect();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new CommandListener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);
    }
    private void registerCommands() {
        getCommand("hub").setExecutor(new HubCommand());
        getCommand("report").setExecutor(new ReportCommand());
        getCommand("vote").setExecutor(new VoteCommand());
    }

    public void log(String log) {
        getLogger().info(log);
    }
    public static AnarchyServer getAnarchyServer() { return anarchyServer; }
    public static AnarchyCore getInstance() {
        return instance;
    }
    public static List<Player> getLoginPlayers() {
        return loginPlayers;
    }

    public Connection getConnection() {
        return sqlConnection;
    }
    public void sendPlayerToServer(Player player, String server) {

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
    }

}
