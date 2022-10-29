package io.github.senerh.infra.util;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import io.quarkus.security.AuthenticationFailedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.List;

public final class DriveHelper {

    private static final String APPLICATION_NAME = "Benim Pencerem";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = List.of(DriveScopes.DRIVE_READONLY, DriveScopes.DRIVE_PHOTOS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "google-drive-authentication.json";
    private static Drive instance;

    public synchronized static Drive getInstance() {
        if (instance == null) {
            instance = buildInstance();
        }
        return instance;
    }

    private static Drive buildInstance() {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream in = classloader.getResourceAsStream(CREDENTIALS_FILE_PATH);
            if (in == null) {
                throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
            }
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(in).createScoped(SCOPES);

            HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(googleCredentials);

            return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            throw new AuthenticationFailedException("Connection to Google Drive API failed.", e);
        }
    }
}
