package de.germanminer.addon.widgets;

import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.key.InputType;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.key.KeyHandler;
import net.labymod.api.client.gui.screen.widget.Widget;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.DivWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.VerticalListWidget;
import packets.special.InputPromptPacket;
import java.util.function.Consumer;

@AutoActivity
@Link("input-prompt.lss")
public class InputPromptActivity extends Activity {

  private final Consumer<String> onSubmit;
  private final Consumer<Boolean> onCancel;
  private final Component title;
  private Component submitButton;
  private Component cancelButton;
  private String value;

  public InputPromptActivity(InputPromptPacket packet, Consumer<String> onSubmit, Consumer<Boolean> onCancel) {
    this.title = Component.text("§b" + packet.getMessage());
    this.submitButton = Component.text(packet.getButtonSubmit());
    this.cancelButton = Component.text(packet.getButtonCancel());
    this.value = (packet.getValue() == null ? "" : packet.getValue());
    this.onSubmit = onSubmit;
    this.onCancel = onCancel;
  }

  public void initialize(Parent parent) {
    super.initialize(parent);

    final DivWidget wrapper = new DivWidget();
    wrapper.addId("wrapper");

    final VerticalListWidget<Widget> container = new VerticalListWidget<>();
    container.addId("container");

    final ComponentWidget title = ComponentWidget.component(this.title);
    title.addId("title");
    container.addChild(title);

    final TextFieldWidget textField = new TextFieldWidget();
    textField.addId("input");
    textField.setFocused(true);
    textField.setText(this.value, true);
    textField.updateListener(text -> this.value = text);
    container.addChild(textField);

    final DivWidget buttonWrapper = new DivWidget();
    buttonWrapper.addId("buttonWrapper");

    final HorizontalListWidget buttonContainer = new HorizontalListWidget();
    buttonContainer.addId("buttonContainer");

    final ButtonWidget submitButton = ButtonWidget.component(this.submitButton);
    submitButton.addId("submit");
    submitButton.setPressable(() -> {
      this.onSubmit.accept(textField.getText());
      super.displayPreviousScreen();
    });
    buttonContainer.addEntry(submitButton);

    final ButtonWidget cancelButton = ButtonWidget.component(this.cancelButton);
    cancelButton.addId("cancel");
    cancelButton.setPressable(() -> {
      this.onCancel.accept(true);
      super.displayPreviousScreen();
    });
    buttonContainer.addEntry(cancelButton);

    buttonWrapper.addChild(buttonContainer);
    container.addChild(buttonWrapper);
    wrapper.addChild(container);
    this.document.addChild(wrapper);
  }

  @Override
  public boolean keyPressed(Key key, InputType type) {
    if (KeyHandler.isEnter(key) && !this.value.isEmpty()) {
      this.onSubmit.accept(this.value);
      return super.displayPreviousScreen();
    }

    if (key == Key.ESCAPE) {
      this.onCancel.accept(true);
      return super.displayPreviousScreen();
    }

    return super.keyPressed(key, type);
  }
}