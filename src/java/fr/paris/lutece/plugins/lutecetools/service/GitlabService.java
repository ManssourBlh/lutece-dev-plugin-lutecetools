/*
 * Copyright (c) 2002-2017, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */


package fr.paris.lutece.plugins.lutecetools.service;

import fr.paris.lutece.plugins.lutecetools.business.Component;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import java.io.IOException;
import java.util.Map;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;

/**
 * GitlabService
 */
public class GitlabService implements ComponentInfoFiller
{
    private static final String SERVICE_NAME = "Gitlab Info filler service";
    private static final String PROPERTY_GITLAB_URL = "lutecetools.gitlab.url";
    private static final String PROPERTY_GITLAB_ACCOUNT_NAME = "lutecetools.gitlab.account.name";
    private static final String PROPERTY_GITLAB_ACCOUNT_TOKEN = "lutecetools.gitlab.account.token";
    private static final String PROPERTY_GITLAB_GROUPS = "lutecetools.gitlab.groups";
    private static final String DSKEY_PARENT_POM_VERSION = "lutecetools.site_property.globalPom.version";

    private static Map<String, GitlabProject> _mapRepositories;


    /**
     * {@inheritDoc }
     */
    @Override
    public String getName()
    {
        return SERVICE_NAME;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void fill( Component component, StringBuilder sbLogs )
    {
        
    }
    
    public static void getRepositories() throws IOException
    {
        String strUrl = AppPropertiesService.getProperty( PROPERTY_GITLAB_URL );
        String strToken = AppPropertiesService.getProperty( PROPERTY_GITLAB_ACCOUNT_TOKEN );
        GitlabAPI gitLabApi = GitlabAPI.connect( strUrl , strToken );
        for (GitlabProject project : gitLabApi.getProjects()) 
        {
            _mapRepositories.put( project.getName() , project );
        }
    }

}
