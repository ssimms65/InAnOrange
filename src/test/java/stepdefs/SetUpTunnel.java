//package stepdefs;
//
//import javax.annotation.Nullable;
//
//
//public class SetUpTunnel (String user, @Nullable String password, String host, int port,
//        int localPort, String remoteHost, int remotePort, @Nullable String prvKeyPath)
//        throws SQLException, JSchException
//        {
//
//        JSch jsch = new JSch();
//        jsch.addIdentity(prvKeyPath);
//        jsch.setConfig("StrictHostKeyChecking", "no");
//
//
//        if (prvKeyPath != null) {
//        jsch.addIdentity(prvKeyPath);
//        }
//
//        Session session = jsch.getSession(user, host, port);
//
//        if (password != null) {
//        session.setPassword(password);
//        }
//
//        System.out.println("Establishing Connection...");
//        session.connect();
//        session.setPortForwardingL(localPort, remoteHost, remotePort);
//        }
