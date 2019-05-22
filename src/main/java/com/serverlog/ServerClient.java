package com.serverlog;

import com.serverlog.dto.DataDto;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * 服务器这边也有一个 Client 为了给其他的 Client 提供一些数据上的支持
 */
public class ServerClient extends Client {

    public ServerClient() {
        // 唯一的一个名字
        uniqueName = "ServerLog";
    }

    @Override
    protected void doSend(@NotNull DataDto dataDto) throws Exception {
        if (DataType.TYPE_GETDATAPROVIDERS.equals(dataDto.getType())) {

        }
    }

    @Override
    public @NotNull List<String> getInterestedType() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void close() {
        // empty
    }

}
