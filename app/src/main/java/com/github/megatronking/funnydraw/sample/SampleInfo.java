package com.github.megatronking.funnydraw.sample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The sample info contains name and sample instance.
 *
 * @author Magetron King
 * @since 18/7/27 22:23
 */

public final class SampleInfo implements Parcelable {

    public final String name;
    public final String sampleClassName;

    public SampleInfo(String name, String sampleClassName) {
        this.name = name;
        this.sampleClassName = sampleClassName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.sampleClassName);
    }

    protected SampleInfo(Parcel in) {
        this.name = in.readString();
        this.sampleClassName = in.readString();
    }

    public static final Creator<SampleInfo> CREATOR = new Creator<SampleInfo>() {
        @Override
        public SampleInfo createFromParcel(Parcel source) {
            return new SampleInfo(source);
        }

        @Override
        public SampleInfo[] newArray(int size) {
            return new SampleInfo[size];
        }
    };

}
