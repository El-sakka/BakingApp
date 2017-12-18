package com.example.mahmoud.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mahmoud on 18/12/17.
 */

public class BakingSteps implements Parcelable{
    int stepId;
    String stepShortDescription;
    String stepDescription;
    String stepVideoUrl;
    String stepThumbnailUrl;

    public BakingSteps(int stepId, String stepShortDescription,
                       String stepDescription,
                       String stepVideoUrl,
                       String stepThumbnailUrl) {
        this.stepId = stepId;
        this.stepShortDescription = stepShortDescription;
        this.stepDescription = stepDescription;
        this.stepVideoUrl = stepVideoUrl;
        this.stepThumbnailUrl = stepThumbnailUrl;
    }

    protected BakingSteps(Parcel in) {
        stepId = in.readInt();
        stepShortDescription = in.readString();
        stepDescription = in.readString();
        stepVideoUrl = in.readString();
        stepThumbnailUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(stepId);
        dest.writeString(stepShortDescription);
        dest.writeString(stepDescription);
        dest.writeString(stepVideoUrl);
        dest.writeString(stepThumbnailUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BakingSteps> CREATOR = new Creator<BakingSteps>() {
        @Override
        public BakingSteps createFromParcel(Parcel in) {
            return new BakingSteps(in);
        }

        @Override
        public BakingSteps[] newArray(int size) {
            return new BakingSteps[size];
        }
    };

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public String getStepShortDescription() {
        return stepShortDescription;
    }

    public void setStepShortDescription(String stepShortDescription) {
        this.stepShortDescription = stepShortDescription;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public String getStepVideoUrl() {
        return stepVideoUrl;
    }

    public void setStepVideoUrl(String stepVideoUrl) {
        this.stepVideoUrl = stepVideoUrl;
    }

    public String getStepThumbnailUrl() {
        return stepThumbnailUrl;
    }

    public void setStepThumbnailUrl(String stepThumbnailUrl) {
        this.stepThumbnailUrl = stepThumbnailUrl;
    }
}
