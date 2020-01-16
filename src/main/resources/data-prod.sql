INSERT IGNORE INTO institution VALUES
    (1, 'SEB Server [ROOT]', null, null, null, 1)
    ;

INSERT IGNORE INTO user VALUES 
    (1, 1, 'super-admin', 'super-admin', 'super-admin', '$2a$08$c2GKYEYoUVXH1Yb8GXVXVu66ltPvbZgLMcVSXRH.LgZNF/YeaYB8m', 'super-admin@nomail.nomail', 'en', 'UTC', 1)
    ;
    
INSERT IGNORE INTO user_role VALUES
    (1, 1, 'SEB_SERVER_ADMIN'),
    (2, 1, 'INSTITUTIONAL_ADMIN'),
    (3, 1, 'EXAM_ADMIN'),
    (4, 1, 'EXAM_SUPPORTER')
    ;
    
INSERT IGNORE INTO view VALUES
    (1, 'general', 4, 1, 0),
    (2, 'user_interface', 12, 2, 0),
    (3, 'browser', 12, 3, 0),
    (4, 'down_upload', 12, 4, 0),
    (5, 'exam', 12, 5, 0),
    (6, 'applications', 12, 6, 0), 
    (7, 'resources', 12, 7, 0), 
    (8, 'network', 12, 8, 0), 
    (9, 'security', 12, 9, 0),
    (10, 'registry', 12, 10, 0),
    (11, 'hooked_keys', 12, 11, 0);
    
INSERT IGNORE INTO configuration_attribute VALUES
    (1, 'hashedAdminPassword', 'PASSWORD_FIELD', null, null, null, null, null),
    (2, 'allowQuit', 'CHECKBOX', null, null, null, null, 'true'),
    (3, 'ignoreExitKeys', 'CHECKBOX', null, null, null, null, 'false'),
    (4, 'hashedQuitPassword', 'PASSWORD_FIELD', null, null, null, null, null),
    (5, 'exitKey1', 'SINGLE_SELECTION', null, '0,1,2,3,4,5,6,7,8,9,10,11', 'ExitKeySequenceValidator', 'resourceLocTextKey=sebserver.examconfig.props.label.exitKey', '2'),
    (6, 'exitKey2', 'SINGLE_SELECTION', null, '0,1,2,3,4,5,6,7,8,9,10,11', 'ExitKeySequenceValidator', 'resourceLocTextKey=sebserver.examconfig.props.label.exitKey', '10'),
    (7, 'exitKey3', 'SINGLE_SELECTION', null, '0,1,2,3,4,5,6,7,8,9,10,11', 'ExitKeySequenceValidator', 'resourceLocTextKey=sebserver.examconfig.props.label.exitKey', '5'),
    
    (8, 'browserViewMode', 'RADIO_SELECTION', null, '0,1,2', null, null, '0'),
    (9, 'enableTouchExit', 'CHECKBOX', null, null, null, null, 'false'),
    (10, 'mainBrowserWindowWidth', 'COMBO_SELECTION', null, '50%,100%,800,1000', 'WindowsSizeValidator', null, '100%'),
    (11, 'mainBrowserWindowHeight', 'COMBO_SELECTION', null, '80%,100%,600,800', 'WindowsSizeValidator', null, '100%'),
    (12, 'mainBrowserWindowPositioning', 'SINGLE_SELECTION', null, '0,1,2', null, null, '1'),
    (13, 'enableBrowserWindowToolbar', 'CHECKBOX', null, null, null, null, 'false'),
    (14, 'hideBrowserWindowToolbar', 'CHECKBOX', null, null, null, null, 'false'),
    (15, 'showMenuBar', 'CHECKBOX', null, null, null, null, 'false'),
    (16, 'showTaskBar', 'CHECKBOX', null, null, null, null, 'true'),
    (17, 'taskBarHeight', 'COMBO_SELECTION', null, '40,60,80', 'IntegerTypeValidator', null, '40'),
    (18, 'showReloadButton', 'CHECKBOX', null, null, null, null, 'true'),
    (19, 'showTime', 'CHECKBOX', null, null, null, null, 'true'),
    (20, 'showInputLanguage', 'CHECKBOX', null, null, null, null, 'false'),
    (21, 'enableZoomPage', 'CHECKBOX', null, null, null, null, 'true'),
    (22, 'enableZoomText', 'CHECKBOX', null, null, null, null, 'true'),
    (23, 'zoomMode', 'RADIO_SELECTION', null, '0,1', null, null, '0'),
    (24, 'audioControlEnabled', 'CHECKBOX', null, null, null, null, 'false'),
    (25, 'audioMute', 'CHECKBOX', null, null, null, null, 'false'),
    (26, 'audioSetVolumeLevel', 'CHECKBOX', null, null, null, null, 'false'),
    (27, 'audioVolumeLevel', 'SLIDER', null, '0,100', null, null, '25'),
    (28, 'allowSpellCheck', 'CHECKBOX', null, null, null, null, 'false'),
    (29, 'allowDictionaryLookup', 'CHECKBOX', null, null, null, null, 'false'),
    (30, 'allowSpellCheckDictionary', 'MULTI_CHECKBOX_SELECTION', null, 'da-DK,en-AU,en-GB,en-US,es-ES,fr-FR,pt-PT,sv-SE,sv-FI', null, null, 'da-DK,en-AU,en-GB,en-US,es-ES,fr-FR,pt-PT,sv-SE,sv-FI'),
    
    (31, 'newBrowserWindowByLinkPolicy', 'RADIO_SELECTION', null, '0,1,2', null, null, '2'),
    (32, 'newBrowserWindowByLinkBlockForeign', 'CHECKBOX', null, null, null, null, 'false'),
    (33, 'newBrowserWindowByLinkWidth', 'COMBO_SELECTION', null, '50%,100%,800,1000', 'WindowsSizeValidator', null, '100%'),
    (34, 'newBrowserWindowByLinkHeight', 'COMBO_SELECTION', null, '80%,100%,600,800', 'WindowsSizeValidator', null, '100%'),
    (35, 'newBrowserWindowByLinkPositioning', 'SINGLE_SELECTION', null, '0,1,2', null, null, '2'),
    (36, 'enablePlugIns', 'CHECKBOX', null, null, null, null, 'true'),
    (37, 'enableJavaScript', 'CHECKBOX', null, null, null, null, 'true'),
    (38, 'enableJava', 'CHECKBOX', null, null, null, null, 'false'),
    (39, 'blockPopUpWindows', 'CHECKBOX', null, null, null, null, 'false'),
    (40, 'allowVideoCapture', 'CHECKBOX', null, null, null, null, 'false'),
    (41, 'allowAudioCapture', 'CHECKBOX', null, null, null, null, 'false'),
    (42, 'allowBrowsingBackForward', 'CHECKBOX', null, null, null, null, 'false'),
    (43, 'newBrowserWindowNavigation', 'CHECKBOX', null, null, null, null, 'true'),
    (44, 'browserWindowAllowReload', 'CHECKBOX', null, null, null, null, 'true'),
    (45, 'newBrowserWindowAllowReload', 'CHECKBOX', null, null, null, null, 'true'),
    (46, 'showReloadWarning', 'CHECKBOX', null, null, null, null, 'true'),
    (47, 'newBrowserWindowShowReloadWarning', 'CHECKBOX', null, null, null, null, 'false'),
    (48, 'removeBrowserProfile', 'CHECKBOX', null, null, null, null, 'false'),
    (49, 'removeLocalStorage', 'CHECKBOX', null, null, null, null, 'false'),
    (50, 'browserUserAgent', 'TEXT_FIELD', null, null, null, null, null),
    (51, 'browserUserAgentWinDesktopMode', 'RADIO_SELECTION', null, '0,1', null, null, '0'),
    (52, 'browserUserAgentWinDesktopModeCustom', 'TEXT_FIELD', null, null, null, null, null),
    (53, 'browserUserAgentWinTouchMode', 'RADIO_SELECTION', null, '0,1,2', null, null, '0'),
    (54, 'browserUserAgentWinTouchModeCustom', 'TEXT_FIELD', null, null, null, null, null),
    (55, 'browserUserAgentMac', 'RADIO_SELECTION', null, '0,1', null, null, '0'),
    (56, 'browserUserAgentMacCustom', 'TEXT_FIELD', null, null, null, null, null),
    (57, 'enableSebBrowser', 'CHECKBOX', null, null, null, null, 'true'),
    (58, 'browserWindowTitleSuffix', 'TEXT_FIELD', null, null, null, null, null),
    
    (59, 'allowDownUploads', 'CHECKBOX', null, null, null, null, 'true'),
    (60, 'downloadDirectoryWin', 'TEXT_FIELD', null, null, null, null, null),
    (61, 'downloadDirectoryOSX', 'TEXT_FIELD', null, null, null, null, null),
    (62, 'openDownloads', 'CHECKBOX', null, null, null, null, 'false'),
    (63, 'chooseFileToUploadPolicy', 'RADIO_SELECTION', null, '0,1,2', null, null, '0'),
    (64, 'downloadPDFFiles', 'CHECKBOX', null, null, null, null, 'true'),
    (65, 'allowPDFPlugIn', 'CHECKBOX', null, null, null, null, 'true'),
    (66, 'downloadAndOpenSebConfig', 'CHECKBOX', null, null, null, null, 'true'),
    
    (67, 'quitURL', 'TEXT_FIELD', null, null, null, null, null),
    (68, 'quitURLConfirm', 'CHECKBOX', null, null, null, null, 'true'),
    (69, 'restartExamUseStartURL', 'CHECKBOX', null, null, null, null, 'false'),
    (70, 'restartExamURL', 'TEXT_FIELD', null, null, null, null, null),
    (71, 'restartExamText', 'TEXT_FIELD', null, null, null, null, null),
    (72, 'restartExamPasswordProtected', 'CHECKBOX', null, null, null, null, 'true'),
    
    (73, 'permittedProcesses', 'TABLE',  null, null, null, null, null),
    (74, 'permittedProcesses.active', 'CHECKBOX', 73, null, null, null, 'true'),
    (75, 'permittedProcesses.os', 'SINGLE_SELECTION', 73, '0,1', null, null, '1'),
    (76, 'permittedProcesses.title', 'TEXT_FIELD', 73, null, null, null, ''),
    (77, 'permittedProcesses.description', 'TEXT_FIELD', 73, null, null, null, ''),
    (78, 'permittedProcesses.executable', 'TEXT_FIELD', 73, null, null, null, ''),
    (79, 'permittedProcesses.originalName', 'TEXT_FIELD', 73, null, null, null, ''),
    (80, 'permittedProcesses.allowedExecutables', 'TEXT_FIELD', 73, null, null, null, ''),
    (81, 'permittedProcesses.path', 'TEXT_FIELD', 73, null, null, null, ''),
    (82, 'permittedProcesses.arguments', 'INLINE_TABLE', 73, '1:active:CHECKBOX|4:argument:TEXT_FIELD', null, null, null),
    (85, 'permittedProcesses.identifier', 'TEXT_FIELD', 73, null, null, null, ''),
    (86, 'permittedProcesses.iconInTaskbar', 'CHECKBOX', 73, null, null, null, 'true'), 
    (87, 'permittedProcesses.autostart', 'CHECKBOX', 73, null, null, null, 'false'),
    (88, 'permittedProcesses.runInBackground', 'CHECKBOX', 73, null, null, null, 'false'),
    (89, 'permittedProcesses.allowUserToChooseApp', 'CHECKBOX', 73, null, null, null, 'false'),
    (90, 'permittedProcesses.strongKill', 'CHECKBOX', 73, null, null, null, 'false'),
    (91, 'allowSwitchToApplications', 'CHECKBOX', null, null, null, null, 'false'),
    (92, 'allowFlashFullscreen', 'CHECKBOX', null, null, null, null, 'false'),
    
    (93, 'prohibitedProcesses', 'TABLE',  null, null, null, null, null),
    (94, 'prohibitedProcesses.active', 'CHECKBOX', 93, null, null, null, 'true'),
    (95, 'prohibitedProcesses.os', 'SINGLE_SELECTION', 93, '0,1', null, null, '1'),
    (96, 'prohibitedProcesses.executable', 'TEXT_FIELD', 93, null, null, null, ''),
    (97, 'prohibitedProcesses.description', 'TEXT_FIELD', 93, null, null, null, ''),
    (98, 'prohibitedProcesses.originalName', 'TEXT_FIELD', 93, null, null, null, ''),
    (99, 'prohibitedProcesses.identifier', 'TEXT_FIELD', 93, null, null, null, ''),
    (100, 'prohibitedProcesses.strongKill', 'CHECKBOX', 93, null, null, null, 'false'),
    (101, 'prohibitedProcesses.currentUser', 'CHECKBOX', 93, null, null, null, 'false'),
    (102, 'prohibitedProcesses.user', 'TEXT_FIELD', 93, null, null, null, null),
    
    (200, 'URLFilterEnable', 'CHECKBOX', null, null, null, null, 'false'),
    (201, 'URLFilterEnableContentFilter', 'CHECKBOX', null, null, null, null, 'false'),
    (202, 'URLFilterRules', 'TABLE',  null, null, null, null, null),
    (203, 'URLFilterRules.active', 'CHECKBOX', 202, null, null, null, 'true'),
    (204, 'URLFilterRules.regex', 'CHECKBOX', 202, null, null, null, 'false'),
    (205, 'URLFilterRules.expression', 'TEXT_FIELD', 202, null, null, null, ''),
    (206, 'URLFilterRules.action', 'SINGLE_SELECTION', 202, '0,1', null, null, '0'),
    
    (210, 'proxySettingsPolicy', 'RADIO_SELECTION', null, '0,1', null, null, '0'),
    (220, 'proxies', 'COMPOSITE_TABLE', null, 'active,TABLE_ENTRY|autoDiscovery,autoConfiguration,http,https,ftp,socks,rtsp', null, null, null),
    (221, 'ExcludeSimpleHostnames', 'CHECKBOX', 220, null, null, 'showInView=true,createDefaultValue=true', 'false'),
    (222, 'ExceptionsList', 'TEXT_AREA', 220, null, null, 'showInView=true,createDefaultValue=true', null),
    (223, 'FTPPassive', 'CHECKBOX', 220, null, null, 'showInView=true,createDefaultValue=true', 'true'),
    (231, 'AutoDiscoveryEnabled', 'CHECKBOX', 220, null, null, 'groupId=autoDiscovery,createDefaultValue=true', 'false'),
    (233, 'AutoConfigurationEnabled', 'CHECKBOX', 220, null, null, 'groupId=autoConfiguration,createDefaultValue=true', 'false'),
    (234, 'AutoConfigurationURL', 'TEXT_FIELD', 220, null, null, 'groupId=autoConfiguration,createDefaultValue=true', null),
    (235, 'AutoConfigurationJavaScript', 'TEXT_AREA', 220, null, null, 'groupId=autoConfiguration,createDefaultValue=true', null),
    (236, 'HTTPEnable', 'CHECKBOX', 220, null, null, 'groupId=http,createDefaultValue=true', 'false'),
    (237, 'HTTPProxy', 'TEXT_FIELD', 220, null, null, 'groupId=http,createDefaultValue=true', null),
    (238, 'HTTPPort', 'INTEGER', 220, null, null, 'groupId=http,createDefaultValue=true', '80'),
    (239, 'HTTPRequiresPassword', 'CHECKBOX', 220, null, null, 'groupId=http,createDefaultValue=true', 'false'),
    (240, 'HTTPUsername', 'TEXT_FIELD', 220, null, null, 'groupId=http,createDefaultValue=true', null),
    (241, 'HTTPPassword', 'TEXT_FIELD', 220, null, null, 'groupId=http,createDefaultValue=true', null),
    (242, 'HTTPSEnable', 'CHECKBOX', 220, null, null, 'groupId=https,createDefaultValue=true', 'false'),
    (243, 'HTTPSProxy', 'TEXT_FIELD', 220, null, null, 'groupId=https,createDefaultValue=true', null),
    (244, 'HTTPSPort', 'INTEGER', 220, null, null, 'groupId=https,createDefaultValue=true', '443'),
    (245, 'HTTPSRequiresPassword', 'CHECKBOX', 220, null, null, 'groupId=https,createDefaultValue=true', 'false'),
    (246, 'HTTPSUsername', 'TEXT_FIELD', 220, null, null, 'groupId=https,createDefaultValue=true', null),
    (247, 'HTTPSPassword', 'TEXT_FIELD', 220, null, null, 'groupId=https,createDefaultValue=true', null),
    (248, 'FTPEnable', 'CHECKBOX', 220, null, null, 'groupId=ftp,createDefaultValue=true', 'false'),
    (249, 'FTPProxy', 'TEXT_FIELD', 220, null, null, 'groupId=ftp,createDefaultValue=true', null),
    (250, 'FTPPort', 'INTEGER', 220, null, null, 'groupId=ftp,createDefaultValue=true', '21'),
    (251, 'FTPRequiresPassword', 'CHECKBOX', 220, null, null, 'groupId=ftp,createDefaultValue=true', 'false'),
    (252, 'FTPUsername', 'TEXT_FIELD', 220, null, null, 'groupId=ftp,createDefaultValue=true', null),
    (253, 'FTPPassword', 'TEXT_FIELD', 220, null, null, 'groupId=ftp,createDefaultValue=true', null),
    (254, 'SOCKSEnable', 'CHECKBOX', 220, null, null, 'groupId=socks,createDefaultValue=true', 'false'),
    (255, 'SOCKSProxy', 'TEXT_FIELD', 220, null, null, 'groupId=socks,createDefaultValue=true', null),
    (256, 'SOCKSPort', 'INTEGER', 220, null, null, 'groupId=socks,createDefaultValue=true', '1080'),
    (257, 'SOCKSRequiresPassword', 'CHECKBOX', 220, null, null, 'groupId=socks,createDefaultValue=true', 'false'),
    (258, 'SOCKSUsername', 'TEXT_FIELD', 220, null, null, 'groupId=socks,createDefaultValue=true', null),
    (259, 'SOCKSPassword', 'TEXT_FIELD', 220, null, null, 'groupId=socks,createDefaultValue=true', null),
    (260, 'RTSPEnable', 'CHECKBOX', 220, null, null, 'groupId=rtsp,createDefaultValue=true', 'false'),
    (261, 'RTSPProxy', 'TEXT_FIELD', 220, null, null, 'groupId=rtsp,createDefaultValue=true', null),
    (262, 'RTSPPort', 'INTEGER', 220, null, null, 'groupId=rtsp,createDefaultValue=true', '554'),
    (263, 'RTSPRequiresPassword', 'CHECKBOX', 220, null, null, 'groupId=rtsp,createDefaultValue=true', 'false'),
    (264, 'RTSPUsername', 'TEXT_FIELD', 220, null, null, 'groupId=rtsp,createDefaultValue=true', null),
    (265, 'RTSPPassword', 'TEXT_FIELD', 220, null, null, 'groupId=rtsp,createDefaultValue=true', null),
    
        
    (300, 'sebServicePolicy', 'RADIO_SELECTION', null, '0,1,2', null, null, '2'),
    (301, 'kioskMode', 'RADIO_SELECTION', null, '0,1,2', null, null, '0'),
    (302, 'allowVirtualMachine', 'CHECKBOX', null, null, null, null, 'false'),
    (303, 'allowScreenSharing', 'CHECKBOX', null, null, null, null, 'false'),
    (304, 'enablePrivateClipboard', 'CHECKBOX', null, null, null, null, 'true'),
    (305, 'enableLogging', 'CHECKBOX', null, null, null, null, 'false'),
    (306, 'logDirectoryWin', 'TEXT_FIELD', null, null, null, null, ''),
    (307, 'logDirectoryOSX', 'TEXT_FIELD', null, null, null, null, 'NSTemporaryDirectory'),
    (308, 'minMacOSVersion', 'SINGLE_SELECTION', null, '0,1,2,3,4,5,6,7', null, null, '0'),
    (309, 'enableAppSwitcherCheck', 'CHECKBOX', null, null, null, null, 'true'),
    (310, 'forceAppFolderInstall', 'CHECKBOX', null, null, null, null, 'true'),
    (311, 'allowUserAppFolderInstall', 'CHECKBOX', null, null, null, null, 'false'),
    (312, 'allowSiri', 'CHECKBOX', null, null, null, null, 'false'),
    (313, 'detectStoppedProcess', 'CHECKBOX', null, null, null, null, 'true'),
    (314, 'allowDisplayMirroring', 'CHECKBOX', null, null, null, null, 'false'),
    (315, 'allowedDisplaysMaxNumber', 'COMBO_SELECTION', null, '1,2,3', null, null, '1'),
    (316, 'allowedDisplayBuiltin', 'CHECKBOX', null, null, null, null, 'true'),
    (317, 'logLevel', 'SINGLE_SELECTION', null, '0,1,2,3,4', null, null, '1'),
    
    (400, 'insideSebEnableSwitchUser', 'CHECKBOX', null, null, null, null, 'false'),
    (401, 'insideSebEnableLockThisComputer', 'CHECKBOX', null, null, null, null, 'false'),
    (402, 'insideSebEnableChangeAPassword', 'CHECKBOX', null, null, null, null, 'false'),
    (403, 'insideSebEnableStartTaskManager', 'CHECKBOX', null, null, null, null, 'false'),
    (404, 'insideSebEnableLogOff', 'CHECKBOX', null, null, null, null, 'false'),
    (405, 'insideSebEnableShutDown', 'CHECKBOX', null, null, null, null, 'false'),
    (406, 'insideSebEnableVmWareClientShade', 'CHECKBOX', null, null, null, null, 'false'),
    (407, 'insideSebEnableEaseOfAccess', 'CHECKBOX', null, null, null, null, 'false'),
    (408, 'insideSebEnableNetworkConnectionSelector', 'CHECKBOX', null, null, null, null, 'false'),
    
    (500, 'enableEsc', 'CHECKBOX', null, null, null, null, 'false'),
    (501, 'enablePrintScreen', 'CHECKBOX', null, null, null, null, 'false'),
    (502, 'enableCtrlEsc', 'CHECKBOX', null, null, null, null, 'false'),
    (503, 'enableAltEsc', 'CHECKBOX', null, null, null, null, 'false'),
    (504, 'enableAltTab', 'CHECKBOX', null, null, null, null, 'true'),
    (505, 'enableAltF4', 'CHECKBOX', null, null, null, null, 'false'),
    (506, 'enableStartMenu', 'CHECKBOX', null, null, null, null, 'false'),
    (507, 'enableRightMouse', 'CHECKBOX', null, null, null, null, 'false'),
    (508, 'enableAltMouseWheel', 'CHECKBOX', null, null, null, null, 'false'),
    
    (509, 'enableF1', 'CHECKBOX', null, null, null, null, 'false'),
    (510, 'enableF2', 'CHECKBOX', null, null, null, null, 'false'),
    (511, 'enableF3', 'CHECKBOX', null, null, null, null, 'false'),
    (512, 'enableF4', 'CHECKBOX', null, null, null, null, 'false'),
    (513, 'enableF5', 'CHECKBOX', null, null, null, null, 'false'),
    (514, 'enableF6', 'CHECKBOX', null, null, null, null, 'false'),
    (515, 'enableF7', 'CHECKBOX', null, null, null, null, 'false'),
    (516, 'enableF8', 'CHECKBOX', null, null, null, null, 'false'),
    (517, 'enableF9', 'CHECKBOX', null, null, null, null, 'false'),
    (518, 'enableF10', 'CHECKBOX', null, null, null, null, 'false'),
    (519, 'enableF11', 'CHECKBOX', null, null, null, null, 'false'),
    (520, 'enableF12', 'CHECKBOX', null, null, null, null, 'false'),
    
    (800, 'browserMessagingSocket', 'TEXT_FIELD', null, null, null, null, 'ws://localhost:8706'),
    (801, 'browserMessagingPingTime', 'INTEGER', null, null, null, null, '120000'),
    (802, 'allowPreferencesWindow', 'CHECKBOX', null, null, null, null, 'true'),
    (803, 'useAsymmetricOnlyEncryption', 'CHECKBOX', null, null, null, null, 'false'),
    (804, 'touchOptimized', 'CHECKBOX', null, null, null, null, 'false'),
    (805, 'browserScreenKeyboard', 'CHECKBOX', null, null, null, null, 'false'),
    (806, 'newBrowserWindowByScriptPolicy', 'INTEGER', null, null, null, null, '2'),
    (807, 'newBrowserWindowByScriptBlockForeign', 'CHECKBOX', null, null, null, null, 'false'),
    (808, 'monitorProcesses', 'CHECKBOX', null, null, null, null, 'false'),
    (809, 'blacklistURLFilter', 'TEXT_FIELD', null, null, null, null, ''),
    (810, 'whitelistURLFilter', 'TEXT_FIELD', null, null, null, null, ''),
    (812, 'allowWlan', 'CHECKBOX', null, null, null, null, 'false'),
    (813, 'hookKeys', 'CHECKBOX', null, null, null, null, 'true'),
    
    (900, 'examSessionClearCookiesOnEnd', 'CHECKBOX', null, null, null, null, 'false'),
    (901, 'examSessionClearCookiesOnStart', 'CHECKBOX', null, null, null, null, 'false'),
    (902, 'showBackToStartButton', 'CHECKBOX', null, null, null, null, 'true'),
    (903, 'showSettingsInApp', 'CHECKBOX', null, null, null, null, 'false'),
    (904, 'browserUserAgentWinTouchModeIPad', 'TEXT_FIELD', null, null, null, null, 'Mozilla/5.0 (iPad; CPU OS 12_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.1.2 Mobile/15E148 Safari/604.1'),
    (905, 'mobileAllowPictureInPictureMediaPlayback', 'CHECKBOX', null, null, null, null, 'false'),
    (906, 'lockOnMessageSocketClose', 'CHECKBOX', null, null, null, null, 'false'),
    (907, 'enableDrawingEditor', 'CHECKBOX', null, null, null, null, 'false'),
    (908, 'URLFilterMessage', 'RADIO_SELECTION', null, '0,1', null, null, '0'),
    (909, 'allowDictation', 'CHECKBOX', null, null, null, null, 'false'),
    (910, 'allowUserSwitching', 'CHECKBOX', null, null, null, null, 'false'),
    (911, 'allowiOSBetaVersionNumber', 'SINGLE_SELECTION', null, '0,12', null, null, '0'),
    (912, 'allowiOSVersionNumberMajor', 'SINGLE_SELECTION', null, '9,10,11,12', null, null, '9'),
    (913, 'allowiOSVersionNumberMinor', 'INTEGER', null, null, null, null, '3'),
    (914, 'allowiOSVersionNumberPatch', 'INTEGER', null, null, null, null, '5'),
    (915, 'browserURLSalt', 'CHECKBOX', null, null, null, null, 'true'),
    (916, 'examKeySalt', 'TEXT_FIELD', null, null, null, null, null),
    (917, 'browserUserAgentiOS', 'SINGLE_SELECTION', null, '0,1,2,3', null, null, '0'),
    (918, 'browserUserAgentiOSCustom', 'TEXT_FIELD', null, null, null, null, ''),
    (919, 'browserWindowShowURL', 'SINGLE_SELECTION', null, '0,1,2,3', null, null, '0'),
    (920, 'mobileAllowQRCodeConfig', 'CHECKBOX', null, null, null, null, 'false'),
    (921, 'mobileAllowSingleAppMode', 'CHECKBOX', null, null, null, null, 'false'),
    (922, 'mobileEnableASAM', 'CHECKBOX', null, null, null, null, 'true'),
    (923, 'mobileEnableGuidedAccessLinkTransform', 'CHECKBOX', null, null, null, null, 'false'),
    (924, 'mobilePreventAutoLock', 'CHECKBOX', null, null, null, null, 'true'),
    (925, 'mobileShowSettings', 'CHECKBOX', null, null, null, null, 'false'),
    (926, 'mobileStatusBarAppearance', 'SINGLE_SELECTION', null, '0,1,2', null, null, '1'),
    (927, 'mobileStatusBarAppearanceExtended', 'SINGLE_SELECTION', null, '0,1,2,3,4', null, null, '1'),
    (928, 'newBrowserWindowShowURL', 'SINGLE_SELECTION', null, '0,1,2,3', null, null, '1'),
    (929, 'pinEmbeddedCertificates', 'CHECKBOX', null, null, null, null, 'false'),
    (930, 'sendBrowserExamKey', 'CHECKBOX', null, null, null, null, 'false'),
    (931, 'showNavigationButtons', 'CHECKBOX', null, null, null, null, 'false'),
    (932, 'showScanQRCodeButton', 'CHECKBOX', null, null, null, null, 'false'),
    (933, 'startResource', 'TEXT_FIELD', null, null, null, null, ''),
    
    (1000, 'originatorVersion', 'TEXT_FIELD', null, null, null, null, 'SEB_Server_0.3.0'),
    (1001, 'sebConfigPurpose', 'RADIO_SELECTION', null, '0,1', null, null, '0')
    
    ;
    
INSERT IGNORE INTO orientation VALUES 
    (1, 1, 0, 1, null, 1, 1, 1, 2, 'LEFT'),
    (2, 2, 0, 1, null, 1, 3, 1, 1, 'LEFT'),
    (3, 3, 0, 1, null, 1, 4, 1, 1, 'LEFT'),
    (4, 4, 0, 1, null, 1, 5, 1, 2, 'LEFT'),
    (5, 5, 0, 1, 'exitSequence', 2, 1, 1, 1, 'NONE'),
    (6, 6, 0, 1, 'exitSequence', 2, 2, 1, 1, 'NONE'),
    (7, 7, 0, 1, 'exitSequence', 2, 3, 1, 1, 'NONE'),

    (8, 8, 0, 2, 'browserViewMode', 0, 0, 3, 3, 'NONE'),
    (9, 9, 0, 2, 'browserViewMode', 3, 2, 4, 1, 'NONE'),
    (10, 10, 0, 2, 'winsize', 1, 4, 2, 1, 'LEFT'),
    (11, 11, 0, 2, 'winsize', 1, 5, 2, 1, 'LEFT'),
    (12, 12, 0, 2, 'winsize', 5, 4, 2, 1, 'LEFT_SPAN'),
    (13, 13, 0, 2, 'wintoolbar', 0, 6, 3, 1, 'NONE'),
    (14, 14, 0, 2, 'wintoolbar', 3, 6, 4, 1, 'NONE'),
    (15, 15, 0, 2, 'wintoolbar', 0, 7, 3, 1, 'NONE'),
    (16, 16, 0, 2, 'taskbar', 0, 9, 3, 1, 'NONE'),
    (17, 17, 0, 2, 'taskbar', 5, 9, 2, 1, 'LEFT_SPAN'),
    (18, 18, 0, 2, 'taskbar', 0, 10, 3, 1, 'NONE'),
    (19, 19, 0, 2, 'taskbar', 0, 11, 3, 1, 'NONE'),
    (20, 20, 0, 2, 'taskbar', 0, 12, 3, 1, 'NONE'),
    (21, 21, 0, 2, 'zoom', 0, 14, 3, 1, 'NONE'),
    (22, 22, 0, 2, 'zoom', 0, 15, 3, 1, 'NONE'),
    (23, 23, 0, 2, 'zoomMode', 3, 14, 4, 1, 'NONE'),
    (24, 24, 0, 2, 'audio', 7, 0, 5, 1, 'NONE'),
    (25, 25, 0, 2, 'audio', 7, 1, 5, 1, 'NONE'),
    (26, 26, 0, 2, 'audio', 7, 2, 5, 1, 'NONE'),
    (27, 27, 0, 2, 'audio', 7, 3, 5, 1, 'NONE'),
    (28, 28, 0, 2, 'spellcheck', 7, 4, 5, 1, 'NONE'),
    (29, 29, 0, 2, 'spellcheck', 7, 5, 5, 1, 'NONE'),
    (30, 30, 0, 2, 'spellcheck', 7, 7, 5, 9, 'TOP'),
    
    (31, 31, 0, 3, 'newBrowserWindow', 0, 0, 3, 3, 'NONE'),
    (32, 32, 0, 3, 'newBrowserWindow', 4, 0, 3, 1, 'NONE'),
    (33, 33, 0, 3, 'newwinsize', 1, 4, 2, 1, 'LEFT'),
    (34, 34, 0, 3, 'newwinsize', 1, 5, 2, 1, 'LEFT'),
    (35, 35, 0, 3, 'newwinsize', 5, 4, 2, 1, 'LEFT_SPAN'),
    (36, 36, 0, 3, 'browserSecurity', 0, 5, 4, 1, 'NONE'),
    (37, 37, 0, 3, 'browserSecurity', 4, 5, 3, 1, 'NONE'),
    (38, 38, 0, 3, 'browserSecurity', 0, 6, 4, 1, 'NONE'),
    (39, 39, 0, 3, 'browserSecurity', 4, 6, 3, 1, 'NONE'),
    (40, 40, 0, 3, 'browserSecurity', 0, 7, 4, 1, 'NONE'),
    (41, 41, 0, 3, 'browserSecurity', 4, 7, 3, 1, 'NONE'),
    (42, 42, 0, 3, 'browserSecurity', 0, 8, 4, 1, 'NONE'),
    (43, 43, 0, 3, 'browserSecurity', 4, 8, 3, 1, 'NONE'),
    (44, 44, 0, 3, 'browserSecurity', 0, 9, 4, 1, 'NONE'),
    (45, 45, 0, 3, 'browserSecurity', 4, 9, 3, 1, 'NONE'),
    (46, 46, 0, 3, 'browserSecurity', 0, 10, 4, 1, 'NONE'),
    (47, 47, 0, 3, 'browserSecurity', 4, 10, 3, 1, 'NONE'),
    (48, 48, 0, 3, 'browserSecurity', 0, 11, 4, 1, 'NONE'),
    (49, 49, 0, 3, 'browserSecurity', 4, 11, 3, 1, 'NONE'),
     
    (50, 50, 0, 3, null, 7, 1, 5, 1, 'TOP'),
    (51, 51, 0, 3, 'userAgentDesktop', 7, 2, 5, 2, 'NONE'),
    (52, 52, 0, 3, 'userAgentDesktop', 7, 3, 5, 1, 'NONE'),
    (53, 53, 0, 3, 'userAgentTouch', 7, 4, 5, 2, 'NONE'),
    (54, 54, 0, 3, 'userAgentTouch', 7, 6, 5, 1, 'NONE'),
    (55, 55, 0, 3, 'userAgentMac', 7, 8, 5, 2, 'NONE'),
    (56, 56, 0, 3, 'userAgentMac', 7, 10, 5, 1, 'NONE'),
    (57, 57, 0, 3, null, 0, 13, 6, 1, 'NONE'),
    (58, 58, 0, 3, null, 7, 13, 5, 1, 'TOP'),
    
    (59, 59, 0, 4, null, 0, 0, 8, 1, 'NONE'),
    (60, 60, 0, 4, null, 3, 1, 5, 1, 'LEFT_SPAN'),
    (61, 61, 0, 4, null, 3, 2, 5, 1, 'LEFT_SPAN'),
    (62, 62, 0, 4, null, 0, 3, 8, 1, 'NONE'),
    (63, 63, 0, 4, null, 0, 5, 8, 2, 'TOP'),
    (64, 64, 0, 4, null, 0, 8, 8, 1, 'NONE'),
    (65, 65, 0, 4, null, 0, 9, 8, 1, 'NONE'),
    (66, 66, 0, 4, null, 0, 10, 8, 1, 'NONE'),
    
    (67, 67, 0, 5, 'quitLink', 0, 1, 8, 1, 'TOP'),
    (68, 68, 0, 5, 'quitLink', 0, 2, 8, 1, 'NONE'),    
    (69, 69, 0, 5, 'backToStart', 0, 4, 8, 1, 'NONE'),
    (70, 70, 0, 5, 'backToStart', 0, 6, 8, 2, 'TOP'),
    (71, 71, 0, 5, 'backToStart', 0, 8, 8, 2, 'TOP'),
    (72, 72, 0, 5, 'backToStart', 0, 10, 8, 1, 'NONE'),
    
    (73, 73, 0, 6, null, 0, 2, 10, 6, 'TOP'),
    (74, 74, 0, 6, null, 1, 1, 1, 1, 'LEFT'), 
    (75, 75, 0, 6, null, 2, 2, 1, 1, 'LEFT'),
    (76, 76, 0, 6, null, 4, 4, 2, 1, 'LEFT'),
    (77, 77, 0, 6, null, 0, 3, 1, 1, 'LEFT'),
    (78, 78, 0, 6, null, 3, 4, 4, 1, 'LEFT'),
    (79, 79, 0, 6, null, 0, 5, 1, 1, 'LEFT'),
    (80, 80, 0, 6, null, 0, 6, 1, 1, 'LEFT'),
    (81, 81, 0, 6, null, 0, 7, 1, 1, 'LEFT'),
    
    (82, 82, 0, 6, null, 0, 8, 1, 3, 'LEFT'),

    (85, 85, 0, 6, null, 0, 8, 1, 1, 'LEFT'),
    (86, 86, 0, 6, null, 0, 7, 1, 1, 'LEFT'),
    (87, 87, 0, 6, null, 0, 9, 1, 1, 'LEFT'),
    (88, 88, 0, 6, null, 0, 10, 1, 1, 'LEFT'),
    (89, 89, 0, 6, null, 0, 11, 1, 1, 'LEFT'),
    (90, 90, 0, 6, null, 0, 12, 1, 1, 'LEFT'),
    (91, 91, 0, 6, null, 0, 0, 5, 1, 'NONE'),
    (92, 92, 0, 6, null, 5, 0, 5, 1, 'NONE'),
    (93, 93, 0, 6, null, 0, 10, 10, 6, 'TOP'),
    (94, 94, 0, 6, null, 1, 1, 1, 1, 'LEFT'), 
    (95, 95, 0, 6, null, 2, 2, 1, 1, 'LEFT'),
    (96, 96, 0, 6, null, 3, 3, 4, 1, 'LEFT'), 
    (97, 97, 0, 6, null, 4, 5, 2, 1, 'LEFT'),
    (98, 98, 0, 6, null, 0, 4, 1, 1, 'LEFT'),
    (99, 99, 0, 6, null, 0, 6, 1, 1, 'LEFT'),
    (100, 100, 0, 6, null, 0, 7, 1, 1, 'LEFT'),
    
    (200, 200, 0, 8, 'urlFilter', 0, 0, 3, 1, 'NONE'),
    (201, 201, 0, 8, 'urlFilter', 3, 0, 4, 1, 'NONE'),
    (202, 202, 0, 8, 'urlFilter', 0, 1, 12, 6, 'NONE'),
    (203, 203, 0, 8, 'urlFilter', 1, 1, 1, 1, 'LEFT'),
    (204, 204, 0, 8, 'urlFilter', 2, 2, 1, 1, 'LEFT'),
    (205, 205, 0, 8, 'urlFilter', 3, 3, 4, 1, 'LEFT'),
    (206, 206, 0, 8, 'urlFilter', 4, 4, 2, 1, 'LEFT'),
    
    (210, 210, 0, 8, 'proxies', 0, 6, 5, 2, 'NONE'),
    (220, 220, 0, 8, 'proxies', 7, 7, 5, 7, 'TOP'),
    (221, 221, 0, 8, 'proxies', 0, 8, 6, 1, 'NONE'),
    (222, 222, 0, 8, 'proxies', 0, 10, 6, 2, 'TOP'),
    (223, 223, 0, 8, 'proxies', 0, 11, 6, 1, 'NONE'),
    
    (231, 231, 0, 8, 'active', 0, 0, 1, 1, 'LEFT'),
    
    (233, 233, 0, 8, 'active', 0, 0, 1, 1, 'LEFT'),
    (234, 234, 0, 8, null, 0, 1, 1, 1, 'LEFT'),
    (235, 235, 0, 8, null, 0, 2, 1, 1, 'LEFT'),
    (236, 236, 0, 8, 'active', 0, 0, 1, 1, 'LEFT'),
    (237, 237, 0, 8, null, 0, 1, 1, 1, 'LEFT'),
    (238, 238, 0, 8, null, 0, 2, 1, 1, 'LEFT'),
    (239, 239, 0, 8, null, 0, 3, 1, 1, 'LEFT'),
    (240, 240, 0, 8, null, 0, 4, 1, 1, 'LEFT'),
    (241, 241, 0, 8, null, 0, 5, 1, 1, 'LEFT'),
    (242, 242, 0, 8, 'active', 0, 0, 1, 1, 'LEFT'),
    (243, 243, 0, 8, null, 0, 1, 1, 1, 'LEFT'),
    (244, 244, 0, 8, null, 0, 2, 1, 1, 'LEFT'),
    (245, 245, 0, 8, null, 0, 3, 1, 1, 'LEFT'),
    (246, 246, 0, 8, null, 0, 4, 1, 1, 'LEFT'),
    (247, 247, 0, 8, null, 0, 5, 1, 1, 'LEFT'),
    (248, 248, 0, 8, 'active', 0, 0, 1, 1, 'LEFT'),
    (249, 249, 0, 8, null, 0, 1, 1, 1, 'LEFT'),
    (250, 250, 0, 8, null, 0, 2, 1, 1, 'LEFT'),
    (251, 251, 0, 8, null, 0, 3, 1, 1, 'LEFT'),
    (252, 252, 0, 8, null, 0, 4, 1, 1, 'LEFT'),
    (253, 253, 0, 8, null, 0, 5, 1, 1, 'LEFT'),
    (254, 254, 0, 8, 'active', 0, 0, 1, 1, 'LEFT'),
    (255, 255, 0, 8, null, 0, 1, 1, 1, 'LEFT'),
    (256, 256, 0, 8, null, 0, 2, 1, 1, 'LEFT'),
    (257, 257, 0, 8, null, 0, 3, 1, 1, 'LEFT'),
    (258, 258, 0, 8, null, 0, 4, 1, 1, 'LEFT'),
    (259, 259, 0, 8, null, 0, 5, 1, 1, 'LEFT'),
    (260, 260, 0, 8, 'active', 0, 0, 1, 1, 'LEFT'),
    (261, 261, 0, 8, null, 0, 1, 1, 1, 'LEFT'),
    (262, 262, 0, 8, null, 0, 2, 1, 1, 'LEFT'),
    (263, 263, 0, 8, null, 0, 3, 1, 1, 'LEFT'),
    (264, 264, 0, 8, null, 0, 4, 1, 1, 'LEFT'),
    (265, 265, 0, 8, null, 0, 5, 1, 1, 'LEFT'),
    
        
    (300, 300, 0, 9, 'servicePolicy', 0, 0, 4, 3, 'NONE'),
    (301, 301, 0, 9, 'kioskMode', 4, 0, 3, 3, 'NONE'),
    (302, 302, 0, 9, null, 0, 5, 4, 1, 'NONE'),
    (303, 303, 0, 9, null, 0, 6, 4, 1, 'NONE'),
    (304, 304, 0, 9, null, 4, 5, 3, 1, 'NONE'),
    (305, 305, 0, 9, 'logging', 0, 8, 6, 1, 'NONE'),
    (306, 306, 0, 9, 'logging', 3, 9, 4, 1, 'LEFT_SPAN'),
    (307, 307, 0, 9, 'logging', 3, 10, 4, 1, 'LEFT_SPAN'),
    (308, 308, 0, 9, 'macSettings', 7, 1, 5, 1, 'TOP'),
    (309, 309, 0, 9, 'macSettings', 7, 2, 5, 1, 'NONE'),
    (310, 310, 0, 9, 'macSettings', 7, 3, 5, 1, 'NONE'),
    (311, 311, 0, 9, 'macSettings', 7, 4, 5, 1, 'NONE'),
    (312, 312, 0, 9, 'macSettings', 7, 5, 5, 1, 'NONE'),
    (313, 313, 0, 9, 'macSettings', 7, 6, 5, 1, 'NONE'),
    (314, 314, 0, 9, 'macSettings', 7, 7, 5, 1, 'NONE'),
    (315, 315, 0, 9, 'macSettings', 7, 9, 5, 1, 'TOP'),
    (316, 316, 0, 9, 'macSettings', 7, 10, 5, 1, 'NONE'),
    (317, 317, 0, 9, 'logging', 3, 11, 4, 1, 'LEFT_SPAN'),
    
    (400, 400, 0, 10, 'registry', 0, 1, 4, 1, 'NONE'),
    (401, 401, 0, 10, 'registry', 0, 2, 4, 1, 'NONE'),
    (402, 402, 0, 10, 'registry', 0, 3, 4, 1, 'NONE'),
    (403, 403, 0, 10, 'registry', 0, 4, 4, 1, 'NONE'),
    (404, 404, 0, 10, 'registry', 0, 5, 4, 1, 'NONE'),
    (405, 405, 0, 10, 'registry', 0, 6, 4, 1, 'NONE'),
    (406, 406, 0, 10, 'registry', 0, 7, 4, 1, 'NONE'),
    (407, 407, 0, 10, 'registry', 0, 8, 4, 1, 'NONE'),
    (408, 408, 0, 10, 'registry', 0, 9, 4, 1, 'NONE'),
    
    (500, 500, 0, 11, 'specialKeys', 0, 1, 3, 1, 'NONE'),
    (501, 501, 0, 11, 'specialKeys', 0, 2, 3, 1, 'NONE'),
    (502, 502, 0, 11, 'specialKeys', 0, 3, 3, 1, 'NONE'),
    (503, 503, 0, 11, 'specialKeys', 0, 4, 3, 1, 'NONE'),
    (504, 504, 0, 11, 'specialKeys', 0, 5, 3, 1, 'NONE'),
    (505, 505, 0, 11, 'specialKeys', 0, 6, 3, 1, 'NONE'),
    (506, 506, 0, 11, 'specialKeys', 0, 7, 3, 1, 'NONE'),
    (507, 507, 0, 11, 'specialKeys', 0, 8, 3, 1, 'NONE'),
    (508, 508, 0, 11, 'specialKeys', 0, 9, 3, 1, 'NONE'),
    
    (509, 509, 0, 11, 'functionKeys', 3, 1, 3, 1, 'NONE'),
    (510, 510, 0, 11, 'functionKeys', 3, 2, 3, 1, 'NONE'),
    (511, 511, 0, 11, 'functionKeys', 3, 3, 3, 1, 'NONE'),
    (512, 512, 0, 11, 'functionKeys', 3, 4, 3, 1, 'NONE'),
    (513, 513, 0, 11, 'functionKeys', 3, 5, 3, 1, 'NONE'),
    (514, 514, 0, 11, 'functionKeys', 3, 6, 3, 1, 'NONE'),
    (515, 515, 0, 11, 'functionKeys', 3, 7, 3, 1, 'NONE'),
    (516, 516, 0, 11, 'functionKeys', 3, 8, 3, 1, 'NONE'),
    (517, 517, 0, 11, 'functionKeys', 3, 9, 3, 1, 'NONE'),
    (518, 518, 0, 11, 'functionKeys', 3, 10, 3, 1, 'NONE'),
    (519, 519, 0, 11, 'functionKeys', 3, 11, 3, 1, 'NONE'),
    (520, 520, 0, 11, 'functionKeys', 3, 12, 3, 1, 'NONE')
    
    ;