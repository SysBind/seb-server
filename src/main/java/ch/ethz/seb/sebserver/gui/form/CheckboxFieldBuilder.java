/*
 * Copyright (c) 2019 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gui.form;

import org.apache.commons.lang3.BooleanUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ch.ethz.seb.sebserver.gui.service.i18n.LocTextKey;

public class CheckboxFieldBuilder extends FieldBuilder<String> {

    protected CheckboxFieldBuilder(final String name, final LocTextKey label, final String value) {
        super(name, label, value);
    }

    @Override
    void build(final FormBuilder builder) {
        final boolean readonly = builder.readonly || this.readonly;
        final Label lab = (this.label != null)
                ? builder.labelLocalized(
                        builder.formParent,
                        this.label,
                        this.defaultLabel,
                        this.spanLabel)
                : null;

        final Composite fieldGrid = Form.createFieldGrid(builder.formParent, this.spanInput);
        final Button checkbox = builder.widgetFactory.buttonLocalized(
                fieldGrid,
                SWT.CHECK,
                null, null);

        final GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, true);
        checkbox.setLayoutData(gridData);
        checkbox.setSelection(BooleanUtils.toBoolean(this.value));

        if (readonly) {
            checkbox.setEnabled(false);
        }

        builder.form.putField(this.name, lab, checkbox);
    }

}
