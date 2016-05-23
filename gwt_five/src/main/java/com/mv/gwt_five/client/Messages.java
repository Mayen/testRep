package com.mv.gwt_five.client;

import com.google.gwt.i18n.client.LocalizableResource.Generate;

@Generate(format = "com.google.gwt.i18n.server.PropertyCatalogFactory")
public interface Messages extends com.google.gwt.i18n.client.Messages {
  
  @DefaultMessage("name please")
  String nameField();

  @DefaultMessage("Send Me")
  String sendButton();
}
