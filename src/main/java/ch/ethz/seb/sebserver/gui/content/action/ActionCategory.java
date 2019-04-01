/*
 * Copyright (c) 2019 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gui.content.action;

import ch.ethz.seb.sebserver.gui.service.i18n.LocTextKey;

public enum ActionCategory {
    FORM(null, 0),
    INSTITUTION_LIST(new LocTextKey("sebserver.institution.list.actions"), 1),
    USER_ACCOUNT_LIST(new LocTextKey("sebserver.useraccount.list.actions"), 1),
    LMS_SETUP_LIST(new LocTextKey("sebserver.lmssetup.list.actions"), 1),
    QUIZ_LIST(new LocTextKey("sebserver.quizdiscovery.list.actions"), 1),
    EXAM_LIST(new LocTextKey("sebserver.exam.list.actions"), 1),
    INDICATOR_LIST(new LocTextKey("sebserver.exam.indicator.list.actions"), 1),
    VARIA(new LocTextKey("sebserver.overall.action.category.varia"), 100),
    ;

    private ActionCategory(final LocTextKey title, final int slotPosition) {
        this.title = title;
        this.slotPosition = slotPosition;
    }

    public final LocTextKey title;
    public final int slotPosition;
}