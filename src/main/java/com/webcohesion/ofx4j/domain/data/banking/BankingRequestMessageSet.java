/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webcohesion.ofx4j.domain.data.banking;

import com.webcohesion.ofx4j.domain.data.MessageSetType;
import com.webcohesion.ofx4j.domain.data.RequestMessageSet;
import com.webcohesion.ofx4j.domain.data.RequestMessage;
import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.ChildAggregate;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "BANKMSGSRQV1" )
public class BankingRequestMessageSet extends RequestMessageSet {

  private List<BankStatementRequestTransaction> statementRequests;

  public MessageSetType getType() {
    return MessageSetType.banking;
  }

  @ChildAggregate(order = 0)
  public List<BankStatementRequestTransaction> getStatementRequests() {
    return statementRequests;
  }
  
  public void setStatementRequests(List<BankStatementRequestTransaction> statementRequests) {
    this.statementRequests = statementRequests;
  }
  
  /**
   * The statement request.
   *
   * @return The statement request.
   */
  public BankStatementRequestTransaction getStatementRequest() {
    return statementRequests == null || statementRequests.isEmpty() ? null : statementRequests.get(0);
  }

  /**
   * The statement request.
   *
   * @param statementRequest The statement request.
   */
  public void setStatementRequest(BankStatementRequestTransaction statementRequest) {
    this.statementRequests = Collections.singletonList(statementRequest);
  }

  // Inherited.
  public List<RequestMessage> getRequestMessages() {
    ArrayList<RequestMessage> requestMessages = new ArrayList<RequestMessage>();
    if (statementRequests != null) {
      requestMessages.addAll(statementRequests);
    }
    return requestMessages;
  }
}
