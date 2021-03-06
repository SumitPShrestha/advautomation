page(name: 'Global Search') {// Page Name to provide test data
    environment(name: 'SIT') {// environment or stage
        section('Search') {// section
            eventId '56824'
            incidentId 'REF000022'
            eventStatus 'Open'
            eventOwner 'Manish'
            patientFirstName 'Jonas'
            patientLastName 'Smith'
            patientID 'PID12399'
            patientDOB '12-13-1989'
            patientDOBVer '12/13/89'
            majorClient 'Test Major Client 01'
            client 'Test Client 01'
            employerGroup 'Test C01BG01 EG 01'
        }
        section('Add') {
//            Incident_ReferralFrom ''
        }
    }
    environment(name: 'PROD_QA') {
        section('GlobalSearchPage') {
            eventId '56824'
            incidentId 'REF000022'
            eventStatus 'Open'
            patientFirstName 'Jonas'
            patientLastName 'Smith'
            patientID 'PID12399'
            patientDOB '12-13-1989'
            patientDOBVer '12/13/89'
            majorClient 'Test Major Client 01'
            client 'Test Client 01'
            employerGroup 'Test C01BG01 EG 01'
        }
        section('ViewEventPage') {
//            Incident_ReferralFrom ''
        }
    }


}

