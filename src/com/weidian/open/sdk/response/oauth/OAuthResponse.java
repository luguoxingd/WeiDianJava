package com.weidian.open.sdk.response.oauth;

import com.weidian.open.sdk.response.AbstractResponse;

public class OAuthResponse extends AbstractResponse {

  private OAuthResult result;

  public OAuthResult getResult() {
    return result;
  }

  public void setResult(OAuthResult result) {
    this.result = result;
  }

}

