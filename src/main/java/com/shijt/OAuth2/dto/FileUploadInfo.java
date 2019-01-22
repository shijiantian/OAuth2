package com.shijt.OAuth2.dto;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashSet;

public class FileUploadInfo {
    private int      resumableChunkSize;
    private long     resumableTotalSize;
    private String   resumableIdentifier;
    private String   resumableFileName;
    private String   resumableRelativePath;
    private String   resumableFilePath;
    public HashSet<Integer> uploadedChunks = new HashSet<>();


    public String getResumableFileName() {
        return resumableFileName;
    }

    public void setResumableFileName(String resumableFileName) {
        this.resumableFileName = resumableFileName;
    }

    public String getResumableFilePath() {
        return resumableFilePath;
    }

    public void setResumableFilePath(String resumableFilePath) {
        this.resumableFilePath = resumableFilePath;
    }


    public int getResumableChunkSize() {
        return resumableChunkSize;
    }

    public void setResumableChunkSize(int resumableChunkSize) {
        this.resumableChunkSize = resumableChunkSize;
    }

    public long getResumableTotalSize() {
        return resumableTotalSize;
    }

    public void setResumableTotalSize(long resumableTotalSize) {
        this.resumableTotalSize = resumableTotalSize;
    }

    public String getResumableIdentifier() {
        return resumableIdentifier;
    }

    public void setResumableIdentifier(String resumableIdentifier) {
        this.resumableIdentifier = resumableIdentifier;
    }

    public String getResumableRelativePath() {
        return resumableRelativePath;
    }

    public void setResumableRelativePath(String resumableRelativePath) {
        this.resumableRelativePath = resumableRelativePath;
    }

    public boolean vaild(){
        if (resumableChunkSize < 0 || resumableTotalSize < 0
                || StringUtils.isEmpty(resumableIdentifier)
                || StringUtils.isEmpty(resumableFileName)
                || StringUtils.isEmpty(resumableRelativePath)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkIfUploadFinished() {
        //check if upload finished
        int count = (int) Math.ceil(((double) resumableTotalSize) / ((double) resumableChunkSize));
        for(int i = 1; i < count; i ++) {
            if (!uploadedChunks.contains(i)) {
                return false;
            }
        }

        //Upload finished, change filename.
        File file = new File(resumableFilePath);
        String new_path = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - ".temp".length());
        file.renameTo(new File(new_path));
        return true;
    }
}
