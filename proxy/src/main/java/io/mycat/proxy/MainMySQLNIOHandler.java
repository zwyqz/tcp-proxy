/**
 * Copyright (C) <2019>  <chen junwen>
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If
 * not, see <http://www.gnu.org/licenses/>.
 */
package io.mycat.proxy;

import io.mycat.proxy.session.MySQLClientSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum MainMySQLNIOHandler implements NIOHandler<MySQLClientSession> {
  INSTANCE;
  protected final static Logger logger = LoggerFactory.getLogger(MainMySQLNIOHandler.class);
  static final MySQLProxyHandler HANDLER = MySQLProxyHandler.INSTANCE;

  @Override
  public void onSocketRead(MySQLClientSession session) throws IOException {
    HANDLER.onBackendResponse(session);
  }

  @Override
  public void onSocketWrite(MySQLClientSession session) throws IOException {
    session.writeToChannel();
  }

  @Override
  public void onWriteFinished(MySQLClientSession session) throws IOException {
    HANDLER.onBackendWriteFinished(session);
  }

  @Override
  public void onSocketClosed(MySQLClientSession session, boolean normal) {
    try {
      HANDLER.onBackendClosed(session, normal);
    } catch (IOException e) {
      logger.warn("MySQL Session {} onSocketClosed caught err ", session, e);
    }
  }
}