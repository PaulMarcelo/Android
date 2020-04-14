package ec.com.paul.arquitecturamvvm.utils;

import androidx.lifecycle.LiveData;

/**
 * Created by Paul Yaguachi on 30/10/2019.
 * Paul Local
 */
public class AbsentLiveData extends LiveData {

    private AbsentLiveData(){
        postValue(null);
    }

    public static <T> LiveData<T> create(){
        return new AbsentLiveData();
    }
}