package com.hsf.music.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 音乐播放的服务组件
 * 实现功能:
 * 播放
 * 暂停
 * 下一首
 * 上一首
 * 获取当前歌曲的播放进度
 * <p/>
 * 需要在AndroidManifest.xml添加以下代码:
 * <service
 * android:name=".PlayService"
 * android:enabled="true"
 * android:exported="true">
 * </service>
 * <p/>
 * 实现功能(播放模式play_mode):
 * 顺序播放
 * 随机播放
 * 单曲循环
 */
public class PlayerService extends Service {
    public PlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
