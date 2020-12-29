<template lang='pug'>
	v-btn.mr-4.botao(:disabled="disabled", color='#3949AB') {{label}}
</template>

<script>
	export default {
		name: 'Botao',
		props:{
			disabled:{
				type:Boolean,
				default: false
			},
			label:{
				type:String,
				default:'Salvar'
			},

		}
	}
</script>

<style scoped>
	.botao{
		color: white !important;
	}
</style>