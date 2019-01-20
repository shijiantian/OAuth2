package com.shijt.OAuth2.dto;

import java.util.HashMap;

public class FileUploadInfoStorage {

    private FileUploadInfoStorage() {}
    private static FileUploadInfoStorage sInstance;

    public static synchronized FileUploadInfoStorage getInstance() {
        if (sInstance == null) {
            sInstance = new FileUploadInfoStorage();
        }
        return sInstance;
    }

    private HashMap<String, FileUploadInfo> mMap = new HashMap<>();

    public synchronized FileUploadInfo get(int resumableChunkSize, long resumableTotalSize,
                                          String resumableIdentifier, String resumableFilename,
                                          String resumableRelativePath, String resumableFilePath) {

        FileUploadInfo info = mMap.get(resumableIdentifier);

        if (info == null) {
            info = new FileUploadInfo();

            info.setResumableChunkSize(resumableChunkSize);
            info.setResumableTotalSize(resumableTotalSize);
            info.setResumableIdentifier(resumableIdentifier);
            info.setResumableFileName(resumableFilename);
            info.setResumableRelativePath(resumableRelativePath);
            info.setResumableFilePath(resumableFilePath);

            mMap.put(resumableIdentifier, info);
        }
        return info;
    }

    public void remove(FileUploadInfo info) {
        mMap.remove(info.getResumableIdentifier());
    }
}
