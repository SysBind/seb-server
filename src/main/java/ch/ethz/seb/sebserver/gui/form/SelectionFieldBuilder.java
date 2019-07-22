/*
 * Copyright (c) 2019 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gui.form;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import ch.ethz.seb.sebserver.gbl.Constants;
import ch.ethz.seb.sebserver.gbl.util.Tuple;
import ch.ethz.seb.sebserver.gui.service.i18n.LocTextKey;
import ch.ethz.seb.sebserver.gui.service.i18n.PolyglotPageService;
import ch.ethz.seb.sebserver.gui.widget.Selection;
import ch.ethz.seb.sebserver.gui.widget.Selection.Type;
import ch.ethz.seb.sebserver.gui.widget.WidgetFactory.CustomVariant;

public final class SelectionFieldBuilder extends FieldBuilder<String> {

    final Supplier<List<Tuple<String>>> itemsSupplier;
    Consumer<Form> selectionListener = null;
    final Selection.Type type;

    SelectionFieldBuilder(
            final Selection.Type type,
            final String name,
            final LocTextKey label,
            final String value,
            final Supplier<List<Tuple<String>>> itemsSupplier) {

        super(name, label, value);
        this.type = type;
        this.itemsSupplier = itemsSupplier;
    }

    public SelectionFieldBuilder withSelectionListener(final Consumer<Form> selectionListener) {
        this.selectionListener = selectionListener;
        return this;
    }

    @Override
    void build(final FormBuilder builder) {
        final Label lab = builder.labelLocalized(builder.formParent, this.label, this.spanLabel);
        if (builder.readonly || this.readonly) {
            buildReadOnly(builder, lab);
        } else {
            buildInput(builder, lab);
        }
    }

    private void buildInput(final FormBuilder builder, final Label lab) {

        final Composite fieldGrid = Form.createFieldGrid(builder.formParent, this.spanInput);
        final String actionKey = (this.label != null) ? this.label.name + ".action" : null;
        final Selection selection = builder.widgetFactory.selectionLocalized(
                this.type,
                fieldGrid,
                this.itemsSupplier,
                null,
                null,
                actionKey);

        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        ((Control) selection).setLayoutData(gridData);
        selection.select(this.value);

        final Label errorLabel = Form.createErrorLabel(fieldGrid);
        builder.form.putField(this.name, lab, selection, errorLabel);

        if (this.selectionListener != null) {
            ((Control) selection).addListener(SWT.Selection, e -> {
                this.selectionListener.accept(builder.form);
            });
        }

        builder.setFieldVisible(this.visible, this.name);
    }

    /* Build the read-only representation of the selection field */
    private void buildReadOnly(final FormBuilder builder, final Label lab) {
        if (this.type == Type.MULTI || this.type == Type.MULTI_COMBO) {
            final Composite composite = new Composite(builder.formParent, SWT.NONE);
            final GridLayout gridLayout = new GridLayout(1, true);
            gridLayout.verticalSpacing = 0;
            gridLayout.horizontalSpacing = 0;
            gridLayout.marginLeft = 0;
            gridLayout.marginHeight = 0;
            gridLayout.marginWidth = 0;
            composite.setLayout(gridLayout);
            if (StringUtils.isBlank(this.value)) {
                final Label label = new Label(composite, SWT.NONE);
                final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, true);
                label.setLayoutData(gridData);
                label.setData(RWT.CUSTOM_VARIANT, CustomVariant.SELECTION_READONLY.key);
                label.setText(this.value);
            } else {
                final Collection<String> keys = Arrays.asList(StringUtils.split(this.value, Constants.LIST_SEPARATOR));
                this.itemsSupplier.get()
                        .stream()
                        .filter(tuple -> keys.contains(tuple._1))
                        .map(tuple -> tuple._1)
                        .forEach(v -> buildReadonlyLabel(composite, v, 0));
            }
        } else {
            builder.form.putField(
                    this.name,
                    lab,
                    buildReadonlyLabel(builder.formParent, this.value, this.spanInput));
            builder.setFieldVisible(this.visible, this.name);
        }
    }

    private Label buildReadonlyLabel(final Composite composite, final String valueKey, final int hspan) {
        final Label label = new Label(composite, SWT.NONE);
        final GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, true, false, hspan, 1);
        gridData.verticalIndent = 0;
        gridData.horizontalIndent = 0;
        label.setLayoutData(gridData);
        label.setData(RWT.CUSTOM_VARIANT, CustomVariant.SELECTION_READONLY.key);

        final Supplier<String> valueSupplier = () -> this.itemsSupplier.get().stream()
                .filter(tuple -> valueKey.equals(tuple._1))
                .findFirst()
                .map(tuple -> tuple._2)
                .orElse(Constants.EMPTY_NOTE);
        final Consumer<Label> updateFunction = l -> l.setText(valueSupplier.get());

        label.setText(valueSupplier.get());
        label.setData(PolyglotPageService.POLYGLOT_WIDGET_FUNCTION_KEY, updateFunction);
        return label;
    }

}