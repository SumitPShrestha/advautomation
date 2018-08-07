navigationPanels() {
    product('SUBRO_POINT') {
        module(name: 'Client Config', linkId: 'Client Config') {
            landingPage(name: 'Clients', linkId: 'Clients', pageTitle: 'Client Search')
        }
        module(name: 'Search', linkId: 'Search') {
            landingPage(name: 'Global Search', linkId: 'Global Search', pageTitle: 'Global Search')
            landingPage(name: 'Incident/Referral', linkId: 'Incident/Referral', pageTitle: 'Incident/Referral Search')
        }
    }
    product('CARE') {
        module(name: 'Client Config', linkId: 'Client Config') {
            landingPage(name: 'Clients', linkId: 'Clients', pageTitle: '')
            landingPage(name: 'Line of Business', linkId: 'Line of Business', pageTitle: '')
            landingPage(name: 'Insurance Product', linkId: 'Insurance Product', pageTitle: '')
            landingPage(name: 'Dupe Logic', linkId: 'Dupe Logic', pageTitle: '')
        }
    }
    product('CARE-DEMO') {
        module(name: '', linkId: '') {
            landingPage(name: '', linkId: '', pageTitle: '')
        }
    }
    product('NOVUSTEST') {
        module(name: '', linkId: '') {
            landingPage(name: '', linkId: '', pageTitle: '')
        }
    }


}