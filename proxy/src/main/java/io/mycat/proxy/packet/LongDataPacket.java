/**
 * Copyright (C) <2019>  <chen junwen>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.mycat.proxy.packet;

public interface LongDataPacket {

    void setLongDataStatementId(long statementId);

    void setLongDataParamId(int paramId);

    void setLongData(byte[] longData);

    long getLongDataStatementId();

    long getLongDataParamId();

    byte[] getLongData();


    default void readPayload(MySQLPacket buffer, int payloadLength) {
        assert buffer.readByte() == 0x18;
        this.setLongDataStatementId(buffer.readFixInt(4));
        this.setLongDataParamId((int) buffer.readFixInt(2));
        this.setLongData(buffer.readBytes(payloadLength - 7));
    }


    default void writePayload(MySQLPacket buffer) {
        buffer.writeByte((byte) 0x18);
        buffer.writeFixInt(4, getLongDataStatementId());
        buffer.writeFixInt(2, getLongDataParamId());
        buffer.writeBytes(getLongData());
    }
}